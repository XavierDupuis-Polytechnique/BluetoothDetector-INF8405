package com.example.bluetoothdetector.main.model

import android.location.Location
import android.os.ParcelUuid
import androidx.compose.runtime.MutableState
import com.example.bluetoothdetector.main.repository.LocationRepository
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
    var location: MutableState<Location?>? = null

    val longitude: Double = -73.781789,
    val latitude: Double = 45.674105,
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

