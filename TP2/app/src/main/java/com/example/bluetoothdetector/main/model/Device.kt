package com.example.bluetoothdetector.main.model

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.ParcelUuid
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bluetoothdetector.R
import java.text.SimpleDateFormat
import java.util.*

// Device with related/pertinent information
@Entity(tableName = "devices")
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
    @ColumnInfo(name = "parcel_uuids")
    var parcelUuids: List<ParcelUuid>? = null,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,
) {
    fun toString(context: Context): String {
        return  "${context.getString(R.string.device_name)} : $name\n" +
                "${context.getString(R.string.device_mac_address)} : $macAddress\n" +
                "${context.getString(R.string.date)} : $date\n" +
                "${context.getString(R.string.device_class)} : $bluetoothClass\n" +
                "${context.getString(R.string.device_type)} : $type\n" +
                "${context.getString(R.string.device_bond_state)} : $bondState\n" +
                "${context.getString(R.string.latitude)} : ${location?.latitude}\n" +
                "${context.getString(R.string.longitude)} : ${location?.longitude}\n"
    }

    companion object {
        private const val devicePrefix = "Device"
        private var currentDeviceId = 0

        // Generates generic device name
        fun generateDeviceName(): String {
            return "${devicePrefix}${currentDeviceId++}"
        }

        // Formats date (simplifies date & time)
        @SuppressLint("SimpleDateFormat")
        private val DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        fun formatDate(device: Device): String {
            return DateFormat.format(device.date)
        }

        // Formats location (trims digits)
        fun formatLocation(value: Double, digits: Int = 6): String {
            return value.format(digits)
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
