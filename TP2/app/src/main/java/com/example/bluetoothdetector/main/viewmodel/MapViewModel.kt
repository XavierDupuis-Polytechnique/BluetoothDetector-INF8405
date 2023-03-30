package com.example.bluetoothdetector.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.repository.DeviceRepository
import com.example.bluetoothdetector.main.repository.LocationRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    locationRepository: LocationRepository,
    private val deviceRepository: DeviceRepository
) : ViewModel() {
    val location = locationRepository.currentLocation
    val devices = deviceRepository.devices
    fun markerClick(device: Device): Boolean {
        println("MARKER CLICK for $device")
        deviceRepository.highlight(device)
        return true
    }

    fun mapClick(latLng: LatLng) {
        println("MAP CLICK")
        deviceRepository.highlight(null)
    }

}