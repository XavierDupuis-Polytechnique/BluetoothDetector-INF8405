package com.example.bluetoothdetector.main.model

import android.annotation.SuppressLint
import android.location.Location
import android.os.ParcelUuid
import java.text.SimpleDateFormat
import java.util.*

// TODO : REMOVE
val addresses = listOf(
    "24:1D:57:84:04:35",
//    MacAddress.fromString("12:34:56:78:90:ab"),
)

data class Device(
    var name: String = generateDeviceName(),
    var macAddress: String = addresses[0],
    val date: Date = Date(),
    var bluetoothClass: String? = null,
    var type: String? = null,
    var bondState: String? = null,
    var uuids: Array<ParcelUuid>? = null,
    var location: Location? = null
) {
    override fun toString(): String {
        return "name : $name\n" +
                "macAddress: $macAddress\n" +
                "date: $date\n" +
                "bluetooth_class : $bluetoothClass\n" +
                "bluetooth_type : $type\n" +
                "bond_state : $bondState\n" +
                "latitude: ${location?.latitude}\n" +
                "longitude: ${location?.longitude}\n"
    }

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
