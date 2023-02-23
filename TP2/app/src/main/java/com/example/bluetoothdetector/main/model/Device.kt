package com.example.bluetoothdetector.main.model

import java.util.*

// TODO : REMOVE
val addresses = listOf(
    "24:1D:57:84:04:35",
//    MacAddress.fromString("12:34:56:78:90:ab"),
)

data class Device(
    val name: String = generateDeviceName(),
    val macAddress: String = addresses[0],
    val date: Date = Date()
    // TODO : add other infos
) {
    companion object {
        private const val devicePrefix = "Device"
        private var currentDeviceId = 0
        fun generateDeviceName(): String {
            return "${devicePrefix}${currentDeviceId++}"
        }
    }
}