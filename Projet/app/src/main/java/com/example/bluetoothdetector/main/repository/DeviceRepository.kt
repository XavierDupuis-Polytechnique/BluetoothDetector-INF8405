package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.sources.DistributedDeviceSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

// Manages all operations related to devices
@Singleton
class DeviceRepository @Inject constructor(
    private val context: Context,
    private val collectionSource: /*CollectionSource<Device, String>*/ DistributedDeviceSource
) {
    // Holds the current mac addresses mapped to the related device
    val devices: MutableMap<String, Device> = mutableStateMapOf()

    // Holds the current device count
    val deviceCount: Int = devices.size

    // Holds the favorite devices
    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())

    // Holds the highlighted device (none if null)
    val highlightedDevice = mutableStateOf<Device?>(null)

    init {
        registerDeviceSourceChange()
    }

    // Register a devices list / favorites change (from collectionSource)
    private fun registerDeviceSourceChange() {
        CoroutineScope(Dispatchers.IO).launch {
            collectionSource.onDeviceCollectionChange.collectLatest {
                onDeviceSourceChange()
            }
        }
        onDeviceSourceChange()
    }

    // Clear and register new devices when source changes
    private fun onDeviceSourceChange() {
        devices.clear()
        favoriteDevices.value = setOf()
        collectionSource.populate(
            devices,
            favoriteDevices,
            { it.macAddress },
            { it.isFavorite },
        )
    }

    // Launches an intent safely
    private fun safeLaunchIntent(
        intent: Intent,
        errorMessage: String = context.getString(R.string.start_intent_error),
        duration: Int = Toast.LENGTH_SHORT
    ) {
        try {
            startActivity(context, intent, null)
        } catch (exception: Exception) {
            // Displays toast if exception is thrown
            Toast.makeText(context, errorMessage, duration).show()
            exception.printStackTrace()
        }
    }

    // Launches the share device intent
    fun share(device: Device) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, device.toString(context))
            addFlags(FLAG_ACTIVITY_NEW_TASK)
            type = "text/plain"
        }
        safeLaunchIntent(
            intent,
            context.getString(R.string.start_intent_could_not_share_device)
        )
    }

    // Launches the external navigation activity intent from selected device
    fun getItinerary(device: Device, zoom: Int = 18) {
        device.location?.let {
            // https://developers.google.com/maps/documentation/urls/android-intents
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=${it.latitude},${it.longitude} (${device.name})")
            ).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
            }
            safeLaunchIntent(
                intent,
                context.getString(R.string.start_intent_google_maps_not_unavailable)
            )
        }
    }

    // Remove selected device from view and memory
    fun forgetDevice(device: Device) {
        devices -= device.macAddress
        CoroutineScope(Dispatchers.IO).launch {
            collectionSource.delete(device)
        }
    }

    // Remove all devices from view and memory
    fun forgetAll() {
        devices.clear()
        CoroutineScope(Dispatchers.IO).launch {
            collectionSource.deleteAll()
        }
    }

    // Add selected device to view and memory
    fun addDevice(device: Device) {
        devices += device.macAddress to device
        CoroutineScope(Dispatchers.IO).launch {
            collectionSource.insert(device)
        }
    }

    // Checks if selected device is favorite
    fun isFavorite(device: Device): Boolean {
        return favoriteDevices.value.contains(device)
    }

    // Toggles a device from the favorite set
    fun toggleFavorite(device: Device) {
        val wasFavorite = isFavorite(device)
        device.isFavorite = !wasFavorite
        addDevice(device)
        if (wasFavorite) {
            favoriteDevices.value = favoriteDevices.value.minus(device)
        } else {
            favoriteDevices.value = favoriteDevices.value.plus(device)
        }
    }

    // Highlights a device (none if null)
    fun highlight(device: Device?) {
        highlightedDevice.value = device
    }

    // Check if selected device is highlighted
    fun isHighlighted(device: Device): Boolean {
        return highlightedDevice.value === device
    }
}