package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.content.Intent
import android.location.Location
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
    val devices: MutableMap<String, Device> = mutableStateMapOf(
        // TODO REMOVE
        "FAKE_MAC_ADDRESS_1" to
                Device(
                    name = "STATIC_1",
                    location = Location("1").apply {
                        latitude = 45.5049
                        longitude = -73.6133 }),
        "FAKE_MAC_ADDRESS_2" to
                Device(
                    name = "STATIC_2",
                    location = Location("2").apply {
                        latitude = 45.5046
                        longitude = -73.6132 }),
    )

    init {
        CoroutineScope(Dispatchers.IO).launch {
            deviceDao.observeDeviceCount().collect {
                println("STORED DEVICE COUNT $it")
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            val savedDevices = deviceDao.getAll().associateBy {
                    device -> device.macAddress
            }
            safeDeviceOperation {
                devices.putAll(savedDevices)
            }
        }
    }

    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())

    val highlightedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            // TODO : ADD OTHER INFORMATION
            putExtra(Intent.EXTRA_TEXT, device.name)
            type = "text/plain"
        }
        startActivity(context, shareIntent, null)
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