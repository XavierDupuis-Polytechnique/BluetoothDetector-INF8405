package com.example.bluetoothdetector.main.model

import android.annotation.SuppressLint
import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.ParcelUuid
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class Device(
    @PrimaryKey
    val macAddress: String,
    @ColumnInfo(name = "name")
    var name: String = generateDeviceName(),
    @ColumnInfo(name = "date")
    val date: Date = Date(),
    @ColumnInfo(name = "bluetooth_class")
    var bluetoothClass: String? = null,
    @ColumnInfo(name = "bluetooth_type")
    var type: String? = null,
    @ColumnInfo(name = "bluetooth_bond_state")
    var bondState: String? = null,
    @ColumnInfo(name = "location")
    var location: Location? = null,
    @ColumnInfo(name="parcel_uuids")
    var parcelUuids: List<ParcelUuid>? = null,
) {

    companion object {
        private const val devicePrefix = "Device"
        private var currentDeviceId = 0
        fun generateDeviceName(): String {
            return "${devicePrefix}${currentDeviceId++}"
        }

        @SuppressLint("SimpleDateFormat")
        private val DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        fun formatDate(device: Device): String {
            return DateFormat.format(device.date)
        }

        fun formatLocation(value: Double, digits: Int = 6): String {
            return value.format(digits)
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
