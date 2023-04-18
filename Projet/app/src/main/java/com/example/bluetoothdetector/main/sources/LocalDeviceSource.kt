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
abstract class LocalDeviceSource : /*CollectionSource<Device, String>,*/ RoomDatabase() {

    protected abstract val collectionDao: DeviceDao

    companion object {
        val Name: String = LocalDeviceSource::javaClass.name
    }

    // Fills requested map with stored devices data
     fun populate(
        instances: MutableMap<String, Device>,
        favorites: MutableState<Set<Device>>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val savedDevices = getAll()
            val favoriteSavedDevices = savedDevices.filter { it.isFavorite }
            safeOperation {
                instances.putAll(
                    savedDevices.associateBy { device ->
                        device.macAddress
                    }
                )
                favorites.value = favorites.value.plus(favoriteSavedDevices)
            }
        }
    }

    // Observes the stored device count
     fun observeInstanceCount(): Flow<Int> {
        return collectionDao.observeInstanceCount()
    }

    // Retrieves all stored devices
     suspend fun getAll(): List<Device> {
        return collectionDao.getAll()
    }

    // Retrieves selected instance
     suspend fun get(id: String): Device? {
        return collectionDao.getInstanceById(id)
    }

    // Deletes selected device from memory
     suspend fun delete(instance: Device) {
        safeOperation {
            collectionDao.delete(instance)
        }
    }

    // Deletes all devices from memory
     suspend fun deleteAll() {
        safeOperation {
            collectionDao.deleteAll()
        }
    }

    // Inserts selected device from memory
     suspend fun insert(instance: Device) {
        safeOperation {
            collectionDao.insert(instance)
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
