package com.example.bluetoothdetector.main.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DevicesListViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {
    val devices = deviceRepository.devices
    val deviceCount = deviceRepository.deviceCount
    val highlightedDevice = deviceRepository.highlightedDevice
    val favoriteDevices = deviceRepository.favoriteDevices
    var expandedDevice = mutableStateOf<Device?>(null)
    var refresh = deviceRepository.refresh

    fun resetRefresh() {
        if(refresh.value)
        refresh.value = false
    }

    fun share(device: Device) {
        deviceRepository.share(device)
    }

    fun isFavorite(device: Device): Boolean {
        return deviceRepository.isFavorite(device)
    }

    fun toggleFavorite(device: Device) {
        deviceRepository.toggleFavorite(device)
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
        deviceRepository.getItinerary(device)
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

}