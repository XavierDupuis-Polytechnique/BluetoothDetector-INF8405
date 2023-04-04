package com.example.bluetoothdetector.main.domain

import android.location.Location
import android.os.ParcelUuid
import androidx.room.TypeConverter
import java.util.*

// https://developer.android.com/training/data-storage/room/referencing-data

// Converts device non-primitives values from and to JSON format
class DeviceConverter : AbstractConverter() {

    // Date converter (from)
    @TypeConverter
    fun dateFromTimestamp(value: String?): Date? =
        decode(value, Date::class.java)

    // Date converter (to)
    @TypeConverter
    fun dateToTimestamp(value: Date?): String? =
        encode(value)

    // Location converter (from)
    @TypeConverter
    fun locationFromString(value: String?): Location? {
        val latitudeAndLongitude = decode(value, Array<Double>::class.java)
        return Location("").apply {
            latitude = latitudeAndLongitude?.get(0) ?: 0.0
            longitude = latitudeAndLongitude?.get(1) ?: 0.0
        }
    }

    // Location converter (to)
    @TypeConverter
    fun locationToString(value: Location?): String? {
        val latitudeAndLongitude = value?.let { arrayListOf(it.latitude, it.longitude) }
        return encode(latitudeAndLongitude)
    }

    // List<ParcelUuid> converter (from)
    @TypeConverter
    fun parcelUuidsFromString(value: String?): List<ParcelUuid>? =
        decode(value, Array<ParcelUuid>::class.java)

    // List<ParcelUuid> converter (to)
    @TypeConverter
    fun parcelUuidsToString(value: List<ParcelUuid>?): String? =
        encode(value)
}
