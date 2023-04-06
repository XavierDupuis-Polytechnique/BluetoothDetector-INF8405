package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.sources.DeviceSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

// Manages all operations related to devices
@Singleton
class DeviceRepository @Inject constructor(
    private val context: Context,
    private val deviceSource: DeviceSource
) {
    // Holds the current device count
    val deviceCount: Flow<Int> = deviceSource.observeDeviceCount()
    // Holds the current mac addresses mapped to the related device
    val devices: MutableMap<String, Device> = mutableStateMapOf()
    // Holds the favorite devices
    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())
    // Holds the highlighted device (none if null)
    val highlightedDevice = mutableStateOf<Device?>(null)

    init {
        deviceSource.populateDevices(devices, favoriteDevices)
    }


    // Launches an intent safely
    private fun safeLaunchIntent(
        intent: Intent,
        errorMessage: String = "Could not launch intent",
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
            putExtra(Intent.EXTRA_TEXT, device.toString())
            addFlags(FLAG_ACTIVITY_NEW_TASK)
            type = "text/plain"
        }
        safeLaunchIntent(intent, "Could not share device")
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
            safeLaunchIntent(intent, "Google Maps is not installed or is disabled")
        }
    }

    // Remove selected device from view and memory
    fun forgetDevice(device: Device) {
        devices -= device.macAddress
        CoroutineScope(Dispatchers.IO).launch {
            deviceSource.delete(device)
        }
    }

    // Remove all devices from view and memory
    fun forgetAll() {
        devices.clear()
        CoroutineScope(Dispatchers.IO).launch {
            deviceSource.deleteAll()
        }
    }

    // Add selected device to view and memory
    fun addDevice(device: Device) {
        devices += device.macAddress to device
        CoroutineScope(Dispatchers.IO).launch {
            deviceSource.insert(device)
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