package com.example.bluetoothdetector.main.domain

import android.location.Location
import android.os.ParcelUuid
import androidx.room.TypeConverter
import java.util.*

// https://developer.android.com/training/data-storage/room/referencing-data
class DeviceConverter : AbstractConverter() {
    @TypeConverter
    fun dateFromTimestamp(value: String?): Date? {
        return decode(value, Date::class.java)
    }

    @TypeConverter
    fun dateToTimestamp(value: Date?): String? {
        return encode(value)
    }

    @TypeConverter
    fun locationFromString(value: String?): Location? {
        return decode(value, Location::class.java)
    }

    @TypeConverter
    fun locationToString(value: Location?): String? {
        return encode(value)
    }

    @TypeConverter
    fun parcelUuidsFromString(value: String?): List<ParcelUuid>? {
        return decode(value, Array<ParcelUuid>::class.java)
    }

    @TypeConverter
    fun parcelUuidsToString(value: List<ParcelUuid>?): String? {
        return encode(value)
    }
}
