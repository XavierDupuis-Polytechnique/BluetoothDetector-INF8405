package com.example.bluetoothdetector.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    locationRepository: LocationRepository
) : ViewModel() {
    val location = locationRepository.currentLocation
}