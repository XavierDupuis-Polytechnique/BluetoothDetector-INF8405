package com.example.bluetoothdetector.main.viewmodel

import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class DevicesListViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {
    val devices = deviceRepository.devices
    val deviceCount = deviceRepository.deviceCount
    val highlightedDevice = deviceRepository.highlightedDevice
    val favoriteDevices = deviceRepository.favoriteDevices
    var expandedDevice = mutableStateOf<Device?>(null)

    fun share(device: Device) {
        deviceRepository.share(device)
    }

    fun isFavorite(device: Device): Boolean {
        return deviceRepository.isFavorite(device)
    }

    fun toggleFavorite(device: Device) {
        if (isFavorite(device)) {
            favoriteDevices.value = favoriteDevices.value.minus(device)
        } else {
            favoriteDevices.value = favoriteDevices.value.plus(device)
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

    fun forget(device: Device) = viewModelScope.launch {
        deviceRepository.forgetDevice(device)
    }

    fun forgetAll() {
        deviceRepository.forgetAll()
    }

    fun isHighlighted(device: Device): Boolean {
        return deviceRepository.isHighlighted(device)
    }

    // TODO : REMOVE
    fun discoverDevice() {
        Timer().schedule(500L) {
            deviceRepository.addDevice(Device(location = Location("1").apply {
                latitude = 45.5049
                longitude = -73.6133
            }))
        }
    }
}