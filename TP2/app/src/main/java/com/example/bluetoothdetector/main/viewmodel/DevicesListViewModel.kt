package com.example.bluetoothdetector.main.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class DevicesListViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {
    val devices = deviceRepository.devices
    val favoriteDevices = mutableStateOf<Set<Device>>(setOf())
    var expandedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        deviceRepository.share(device)
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