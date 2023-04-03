package com.example.bluetoothdetector.main.domain

import android.location.Location
import android.os.ParcelUuid
import androidx.room.TypeConverter
import java.util.*

// https://developer.android.com/training/data-storage/room/referencing-data
class DeviceConverter : AbstractConverter() {
    @TypeConverter
    fun dateFromTimestamp(value: String?): Date? =
        decode(value, Date::class.java)

    @TypeConverter
    fun dateToTimestamp(value: Date?): String? =
        encode(value)

    @TypeConverter
    fun locationFromString(value: String?): Location? =
        decode(value, Location::class.java)

    @TypeConverter
    fun locationToString(value: Location?): String? =
        encode(value)

    @TypeConverter
    fun parcelUuidsFromString(value: String?): List<ParcelUuid>? =
        decode(value, Array<ParcelUuid>::class.java)

    @TypeConverter
    fun parcelUuidsToString(value: List<ParcelUuid>?): String? =
        encode(value)
}
