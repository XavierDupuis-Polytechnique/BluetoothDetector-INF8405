package com.example.bluetoothdetector.main.sources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bluetoothdetector.main.domain.DeviceConverter
import com.example.bluetoothdetector.main.domain.DeviceDao
import com.example.bluetoothdetector.main.model.Device

@Database(entities = [Device::class], version = 2)
@TypeConverters(DeviceConverter::class)
abstract class DeviceSource : RoomDatabase() {

    abstract val deviceDao: DeviceDao

    companion object {
        val Name: String = DeviceSource::javaClass.name
    }
}
