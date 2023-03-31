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
    init {
        CoroutineScope(Dispatchers.IO).launch {
            deviceDao.observeDeviceCount().collect {
                println("STORED DEVICE COUNT $it")
            }
        }
        CoroutineScope(Dispatchers.Unconfined).launch {
            deviceDao.getAll().apply {
                val d = this.associateBy { device -> device.name }
                // val d = it.associateBy { device -> device.macAddress }
                devices.putAll(d)
                println(devices.values.size)
            }
        }
        CoroutineScope(Dispatchers.Unconfined).launch {
            deviceDao.observeAll().collect {
                val d = it.associateBy { device -> device.name }
                // val d = it.associateBy { device -> device.macAddress }
                devices.putAll(d)
                println(devices.values.size)
            }
        }
    }

    val deviceCount: Flow<Int> = deviceDao.observeDeviceCount()

    val devices: MutableMap<String, Device> = mutableStateMapOf(
        "FAKE_MAC_ADDRESS_1" to Device(location = Location("1").apply {
            latitude = 45.5049
            longitude = -73.6133
        }),
        "FAKE_MAC_ADDRESS_2" to Device(location = Location("2").apply {
            latitude = 45.5046
            longitude = -73.6132
        })
    )

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

    suspend fun forget(device: Device) {
        // safeDeviceOperation {
        //     deviceDao.delete(device)
        // }
        saveDevice(device)
    }

    suspend fun saveDevice(device: Device) {
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