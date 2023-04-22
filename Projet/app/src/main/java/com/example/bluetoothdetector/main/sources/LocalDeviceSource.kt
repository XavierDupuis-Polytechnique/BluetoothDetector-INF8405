package com.example.bluetoothdetector.main.sources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bluetoothdetector.main.domain.DeviceConverter
import com.example.bluetoothdetector.main.domain.DeviceDao
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.sources.CollectionSource.Companion.safeOperation
import kotlinx.coroutines.flow.Flow

// Holds persistent devices data
@Database(entities = [Device::class], version = 7)
@TypeConverters(DeviceConverter::class)
abstract class LocalDeviceSource : /* CollectionSource<Device, String>,*/ RoomDatabase() {

    protected abstract val collectionDao: DeviceDao

    companion object {
        val Name: String = LocalDeviceSource::javaClass.name
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
}
