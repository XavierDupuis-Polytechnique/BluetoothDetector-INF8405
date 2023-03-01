package com.example.bluetoothdetector.main.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.model.Device
import java.util.*
import kotlin.concurrent.schedule

class DevicesListViewModel : ViewModel() {
    // TODO : REDIRECT
    val devices = mutableStateOf(listOf(Device(), Device()))
    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())
    var expandedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        // TODO
    }

    fun isFavorite(device: Device): Boolean {
        return favoriteDevices.value.contains(device)
    }

    fun toggleFavorite(device: Device) {
        if (isFavorite(device)) {
            favoriteDevices.value = favoriteDevices.value.minus(device)
        } else {
            favoriteDevices.value = favoriteDevices.value.plus(device)
        }
        // TODO : REMOVE
        Timer().schedule(500L) {
            devices.value = devices.value.plus(Device())
        }
    }

    fun isExpanded(device: Device): Boolean {
        return expandedDevice.value === device
    }

    fun toggleExpanded(device: Device) {
        if (isExpanded(device))
            expandedDevice.value = null
        else
            expandedDevice.value = device
    }


    fun getItinerary(device: Device) {
        // TODO
    }

    fun forget(device: Device) {
        // TODO
    }
}