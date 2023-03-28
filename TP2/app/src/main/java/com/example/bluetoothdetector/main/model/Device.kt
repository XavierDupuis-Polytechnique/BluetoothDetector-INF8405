package com.example.bluetoothdetector.main.model

import android.os.ParcelUuid

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

    // TODO : add others
    //    val location: Location
    //    val classType: ClassType
    //    val otherInfo: OtherInfo
) {

    companion object {
        private const val devicePrefix = "Device"
        private var currentDeviceId = 0
        fun generateDeviceName(): String {
            return "${devicePrefix}${currentDeviceId++}"
        }
    }
}

