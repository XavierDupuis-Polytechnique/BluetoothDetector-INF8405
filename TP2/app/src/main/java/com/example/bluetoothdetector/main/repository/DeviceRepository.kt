package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.content.Intent
import androidx.annotation.WorkerThread
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import com.example.bluetoothdetector.main.domain.DeviceDao
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.sources.DeviceSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepository @Inject constructor(
    private val context: Context,
    private val deviceDao: DeviceDao
) {
    val deviceCount: Flow<Int> = deviceDao.observeDeviceCount()
    val devices = mutableMapOf<String, Device>(
        "MA1" to Device(),
        "MA2" to Device()
    )

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
        } catch(exception: Exception) {
            println("COULD NOT EXECUTE $operation")
            exception.printStackTrace()
        }
    }
}