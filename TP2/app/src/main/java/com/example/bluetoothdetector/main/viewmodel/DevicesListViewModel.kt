package com.example.bluetoothdetector.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.model.Device

class DevicesListViewModel : ViewModel() {
    // TODO : REDIRECT
    val devices: List<Device> = listOf(Device(), Device(), Device())

    fun share(device: Device) {
        // TODO
    }

    fun toggleFavorite(device: Device) {
        // TODO
    }

    fun getItinerary(device: Device) {
        // TODO
    }
}