package com.example.bluetoothdetector.main.model

import android.location.Location
import com.example.bluetoothdetector.main.repository.LocationRepository
import java.util.*

// TODO : REMOVE
val addresses = listOf(
    "24:1D:57:84:04:35",
//    MacAddress.fromString("12:34:56:78:90:ab"),
)

data class Device(
    val name: String = generateDeviceName(),
    val macAddress: String = addresses[0],
    val date: Date = Date(),

    val longitude: Double = -73.781789,
    val latitude: Double = 45.674105,
    // TODO : add others
    //    val location: Location = generateLocation(),
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