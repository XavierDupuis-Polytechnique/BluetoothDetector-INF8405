package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import com.example.bluetoothdetector.main.domain.DeviceDao
import com.example.bluetoothdetector.main.model.Device
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepository @Inject constructor(
    private val context: Context,
    private val deviceDao: DeviceDao
) {
    val deviceCount: Flow<Int> = deviceDao.observeDeviceCount()
    val devices: MutableMap<String, Device> = mutableStateMapOf()

    init {
        // TODO : REMOVE
        CoroutineScope(Dispatchers.IO).launch {
            deviceDao.observeDeviceCount().collect {
                println("STORED DEVICE COUNT $it")
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            val savedDevices = deviceDao.getAll().associateBy { device ->
                device.macAddress
            }
            safeDeviceOperation {
                devices.putAll(savedDevices)
            }
        }
    }
    // TODO : REMOVE
    // val devices: MutableMap<String, Device> = mutableStateMapOf(
    //     "FAKE_MAC_ADDRESS_1" to Device(location = Location("1").apply {
    //         latitude = 45.5059
    //         longitude = -73.6143
    //     }),
    //     "FAKE_MAC_ADDRESS_2" to Device(location = Location("2").apply {
    //         latitude = 45.5056
    //         longitude = -73.6122
    //     })
    // )

    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())

    val highlightedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, device.toString())
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
            )
            try {
                startActivity(context, intent, null)
            } catch (exception: Exception) {
                Toast.makeText(context, "Google Maps is not installed or is disabled", Toast.LENGTH_SHORT).show()
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
        safeDeviceOperation {
            deviceDao.delete(device)
        }
    }

    fun forgetAll() {
        devices.clear()
        CoroutineScope(Dispatchers.IO).launch {
            deleteAll()
        }
    }

    private suspend fun deleteAll() {
        safeDeviceOperation {
            deviceDao.deleteAll()
        }
    }

    fun addDevice(device: Device) {
        devices[device.macAddress] = device
        CoroutineScope(Dispatchers.IO).launch {
            saveDevice(device)
        }
    }

    private suspend fun saveDevice(device: Device) {
        safeDeviceOperation {
            deviceDao.insert(device)
        }
    }

    private suspend fun safeDeviceOperation(
        operation: suspend () -> Unit
    ) {
        try {
            operation()
        } catch (exception: Exception) {
            println("COULD NOT EXECUTE $operation")
            exception.printStackTrace()
        }
    }

    fun isFavorite(device: Device): Boolean {
        return favoriteDevices.value.contains(device)
    }

    fun highlight(device: Device?) {
        highlightedDevice.value = device
    }

    fun isHighlighted(device: Device): Boolean {
        return highlightedDevice.value === device
    }
}