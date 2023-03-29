package com.example.bluetoothdetector.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.repository.DeviceRepository
import com.example.bluetoothdetector.main.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    locationRepository: LocationRepository,
    deviceRepository: DeviceRepository
) : ViewModel() {
    val location = locationRepository.currentLocation
    val devices = deviceRepository.devices
}