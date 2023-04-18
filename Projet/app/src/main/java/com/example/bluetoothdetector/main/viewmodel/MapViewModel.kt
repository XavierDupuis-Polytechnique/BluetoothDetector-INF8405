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

    // Handles click on a device marker
    fun markerClick(device: Device): Boolean {
        deviceRepository.highlight(device)
        return false
    }

    // Handles click on map, on no marker
    fun mapClick(latLng: LatLng) {
        deviceRepository.highlight(null)
    }

    // Check if selected device is highlighted
    fun isHighlighted(device: Device): Boolean {
        return deviceRepository.isHighlighted(device)
    }

    // Check if selected device is favorite
    fun isFavorite(device: Device): Boolean {
        return deviceRepository.isFavorite(device)
    }

    // Check if any of the localisation permissions is granted
    fun areAnyRequiredPermissionsGranted(
        permissionsState: MultiplePermissionsState
    ): Boolean {
        return permissionsState.permissions.any {
            it.status == PermissionStatus.Granted
        }
    }

    // Provides maps camera starting point
    //  1. Current position
    //  2. Any device position, if previous not available
    //  3. Default position (Polytechnique), if previous not available
    fun getStartPosition(): LatLng {
        return location.value?.toLatLng()
            ?: devices.values.firstOrNull { it.location !== null }?.latLng
            ?: LocationRepository.DefaultLocation.toLatLng()
    }
}

// Converts Location to LatLng
fun Location.toLatLng(): LatLng {
    return LatLng(latitude, longitude)
}

// Converts LatLng to Location
fun LatLng.toLocation(): Location {
    val other = this
    return Location("").apply {
        latitude = other.latitude
        longitude = other.longitude
    }
}