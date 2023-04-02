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
            latitude = 45.5059
            longitude = -73.6143
        }),
        "FAKE_MAC_ADDRESS_2" to Device(location = Location("2").apply {
            latitude = 45.5056
            longitude = -73.6122
        })
    )

    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())

    val highlightedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        deviceSource.share(device)
    }

    fun isFavorite(device: Device): Boolean {
        return favoriteDevices.value.contains(device)
    }

    fun highlight(device: Device?) {
        highlightedDevice.value = device
    }

    fun isHighlighted(device: Device): Boolean {
        return highlightedDevice.value === device
    }
}