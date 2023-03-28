package com.example.bluetoothdetector.main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// TODO : REMOVE
val addresses = listOf(
    "24:1D:57:84:04:35",
//    MacAddress.fromString("12:34:56:78:90:ab"),
)

@Entity
data class Device(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name="name") val name: String = generateDeviceName(),
    @ColumnInfo(name="mac_address") val macAddress: String = addresses[0],
    @ColumnInfo(name="date") val date: Date = Date(),
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