package com.example.bluetoothdetector.main.viewmodel

import androidx.compose.material.MaterialTheme
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.repository.ThemeRepository
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.repository.DeviceRepository
import com.example.bluetoothdetector.main.repository.LocationRepository
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    locationRepository: LocationRepository,
    private val deviceRepository: DeviceRepository,
    themeRepository: ThemeRepository
) : ViewModel() {
    val isDarkTheme = themeRepository.isDarkTheme
    val location = locationRepository.currentLocation
    val devices = deviceRepository.devices
    fun markerClick(device: Device): Boolean {
        deviceRepository.highlight(device)
        return false
    }

    fun mapClick(latLng: LatLng) {
        deviceRepository.highlight(null)
    }

    fun isHighlighted(device: Device): Boolean {
        return deviceRepository.isHighlighted(device)
    }

    fun isFavorite(device: Device): Boolean {
        return deviceRepository.isFavorite(device)
    }

    fun currentPositionMarkerClick(currentLocation: LatLng): Boolean {
        mapClick(currentLocation)
        return false
    }
}