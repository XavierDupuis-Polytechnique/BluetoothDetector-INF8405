package com.example.bluetoothdetector.main.sources

import androidx.compose.runtime.MutableState
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bluetoothdetector.main.domain.DeviceConverter
import com.example.bluetoothdetector.main.domain.DeviceDao
import com.example.bluetoothdetector.main.model.Device
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// Holds persistent devices data
@Database(entities = [Device::class], version = 6)
@TypeConverters(DeviceConverter::class)
abstract class DeviceSource : RoomDatabase() {

    protected abstract val deviceDao: DeviceDao

    companion object {
        val Name: String = DeviceSource::javaClass.name
    }

    // Fills requested map with stored devices data
    fun populateDevices(
        devices: MutableMap<String, Device>,
        favoriteDevices: MutableState<Set<Device>>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val savedDevices = getAll()
            val favoriteSavedDevices = savedDevices.filter { it.isFavorite }
            safeOperation {
                devices.putAll(
                    savedDevices.associateBy { device ->
                        device.macAddress
                    }
                )
                favoriteDevices.value = favoriteDevices.value.plus(favoriteSavedDevices)
            }
        }
    }

    // Observes the stored device count
    fun observeDeviceCount(): Flow<Int> {
        return deviceDao.observeDeviceCount()
    }

    // Retrieves all stored devices
    suspend fun getAll(): List<Device> {
        return deviceDao.getAll()
    }

    // Deletes selected device from memory
    suspend fun delete(device: Device) {
        safeOperation {
            deviceDao.delete(device)
        }
    }

    // Deletes all devices from memory
    suspend fun deleteAll() {
        safeOperation {
            deviceDao.deleteAll()
        }
    }

    // Inserts selected device from memory
    suspend fun insert(device: Device) {
        safeOperation {
            deviceDao.insert(device)
        }
    }

    // Execute a store operation safely
    private suspend fun safeOperation(
        operation: suspend () -> Unit
    ) {
        try {
            operation()
        } catch (exception: Exception) {
            println("COULD NOT EXECUTE $operation")
            exception.printStackTrace()
        }
    }
}
