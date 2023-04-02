package com.example.bluetoothdetector.main.domain

import android.location.Location
import android.os.ParcelUuid
import androidx.room.TypeConverter
import java.util.*

// https://developer.android.com/training/data-storage/room/referencing-data
class DeviceConverter {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun locationFromString(value: String?): Location? {
        return value?.let { Location(value) }
    }

    @TypeConverter
    fun locationToString(location: Location?): String? {
        return location?.toString()
    }

    @TypeConverter
    fun parcelUuidsFromString(value: String?): List<ParcelUuid>? {
        return value?.let { list ->
            list.split(",").map {
                ParcelUuid(UUID.fromString(it))
            }
        }
    }

    @TypeConverter
    fun parcelUuidsToString(parcelUuids: List<ParcelUuid>?): String? {
        return parcelUuids?.joinToString(separator = ",")
    }
}
