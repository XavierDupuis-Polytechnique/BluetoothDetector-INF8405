package com.example.bluetoothdetector.main.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.repository.ThemeRepository
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.repository.DeviceRepository
import com.example.bluetoothdetector.main.repository.LocationRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@OptIn(ExperimentalPermissionsApi::class)
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

    fun areAnyRequiredPermissionsGranted(
        permissionsState: MultiplePermissionsState
    ): Boolean {
        return permissionsState.permissions.any {
            it.status == PermissionStatus.Granted
        }
    }

    fun getStartPosition(): LatLng {
        return location.value?.toLatLng()
            ?: devices.values.firstOrNull { it.location !== null }?.location?.toLatLng()
            ?: LocationRepository.DefaultLocation.toLatLng()
    }
}

private fun Location.toLatLng(): LatLng {
    return LatLng(latitude, longitude)
}