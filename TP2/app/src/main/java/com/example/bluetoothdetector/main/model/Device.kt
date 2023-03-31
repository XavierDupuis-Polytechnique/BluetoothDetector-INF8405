package com.example.bluetoothdetector.main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.annotation.SuppressLint
import android.location.Location
import android.os.ParcelUuid
import androidx.compose.runtime.MutableState
import java.text.SimpleDateFormat

import java.util.*

// TODO : REMOVE
val addresses = listOf(
    "24:1D:57:84:04:35",
//    MacAddress.fromString("12:34:56:78:90:ab"),
)

@Entity
data class Device(
    @PrimaryKey macAddress: String = addresses[0],
    @ColumnInfo(name="name") var name: String = generateDeviceName(),
    @ColumnInfo(name="date") val date: Date = Date(),
    @ColumnInfo(name="bluetooth_class") var bluetoothClass: String? = null,
    @ColumnInfo(name="bluetooth_type") var type: String? = null,
    @ColumnInfo(name="bluetooth_bond_state") var bondState: String? = null,
    @ColumnInfo(name="location") var location: Location? = null
    @ColumnInfo(name="uuids") var uuids: Array<ParcelUuid>? = null,
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
    }
}

