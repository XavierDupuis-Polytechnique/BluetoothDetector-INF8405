package com.example.bluetoothdetector.main.repository

import android.location.Location
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.sources.DeviceSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepository @Inject constructor(
    private val deviceSource: DeviceSource,
) {
    //  TODO : UPDATE / REMOVE
    val a = "SomeRepositoryValue"

    val devices: MutableMap<String, Device> = mutableStateMapOf(
        "FAKE_MAC_ADDRESS_1" to Device(location = Location("1").apply {
            latitude = 45.5049
            longitude = -73.6133
        }),
        "FAKE_MAC_ADDRESS_2" to Device(location = Location("2").apply {
            latitude = 45.5046
            longitude = -73.6132
        })
    )

    val highlightedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        deviceSource.share(device)
    }

    fun highlight(device: Device?) {
        highlightedDevice.value = device
    }
}