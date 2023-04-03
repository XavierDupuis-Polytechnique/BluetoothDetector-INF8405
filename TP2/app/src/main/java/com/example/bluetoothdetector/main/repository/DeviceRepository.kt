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

@Singleton
class DeviceRepository @Inject constructor(
    private val context: Context,
    private val deviceSource: DeviceSource
) {
    val deviceCount: Flow<Int> = deviceSource.observeDeviceCount()
    val devices: MutableMap<String, Device> = mutableStateMapOf()

    init {
        deviceSource.populateDevices(devices)
    }

    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())

    val highlightedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, device.toString())
            addFlags(FLAG_ACTIVITY_NEW_TASK)
            type = "text/plain"
        }
        try {
            startActivity(context, intent, null)
        } catch (exception: Exception) {
            Toast.makeText(context, "Could not share device", Toast.LENGTH_SHORT).show()
            exception.printStackTrace()
        }
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
            try {
                startActivity(context, intent, null)
            } catch (exception: Exception) {
                Toast.makeText(
                    context,
                    "Google Maps is not installed or is disabled",
                    Toast.LENGTH_SHORT
                ).show()
                exception.printStackTrace()
            }
        }
    }

    fun forgetDevice(device: Device) {
        devices.remove(device.macAddress)
        CoroutineScope(Dispatchers.IO).launch {
            deleteDevice(device)
        }
    }

    private suspend fun deleteDevice(device: Device) {
        deviceSource.delete(device)
    }

    fun forgetAll() {
        devices.clear()
        CoroutineScope(Dispatchers.IO).launch {
            deleteAll()
        }
    }

    private suspend fun deleteAll() {
        deviceSource.deleteAll()
    }

    fun addDevice(device: Device) {
        devices[device.macAddress] = device
        CoroutineScope(Dispatchers.IO).launch {
            saveDevice(device)
        }
    }

    private suspend fun saveDevice(device: Device) {
        deviceSource.insert(device)
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