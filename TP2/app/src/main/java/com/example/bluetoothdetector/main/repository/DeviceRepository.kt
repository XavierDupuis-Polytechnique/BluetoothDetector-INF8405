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
import com.example.bluetoothdetector.main.sources.DeviceSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepository @Inject constructor(
    private val context: Context,
    private val deviceSource: DeviceSource
) {
    val deviceCount: Flow<Int> = deviceSource.observeDeviceCount()
    val devices: MutableMap<String, Device> = mutableStateMapOf()
    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())
    val highlightedDevice = mutableStateOf<Device?>(null)

    init {
        deviceSource.populateDevices(devices)
    }


    private fun safeLaunchIntent(
        intent: Intent,
        errorMessage: String = context.getString(R.string.start_intent_error),
        duration: Int = Toast.LENGTH_SHORT
    ) {
        try {
            startActivity(context, intent, null)
        } catch (exception: Exception) {
            Toast.makeText(context, errorMessage, duration).show()
            exception.printStackTrace()
        }
    }

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

    fun forgetDevice(device: Device) {
        devices.remove(device.macAddress)
        CoroutineScope(Dispatchers.IO).launch {
            deviceSource.delete(device)
        }
    }

    fun forgetAll() {
        devices.clear()
        CoroutineScope(Dispatchers.IO).launch {
            deviceSource.deleteAll()
        }
    }

    fun addDevice(device: Device) {
        devices[device.macAddress] = device
        CoroutineScope(Dispatchers.IO).launch {
            deviceSource.insert(device)
        }
    }

    fun isFavorite(device: Device): Boolean {
        return favoriteDevices.value.contains(device)
    }

    fun toggleFavorite(device: Device) {
        if (isFavorite(device)) {
            favoriteDevices.value = favoriteDevices.value.minus(device)
        } else {
            favoriteDevices.value = favoriteDevices.value.plus(device)
        }
    }

    fun highlight(device: Device?) {
        highlightedDevice.value = device
    }

    fun isHighlighted(device: Device): Boolean {
        return highlightedDevice.value === device
    }
}