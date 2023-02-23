package com.example.bluetoothdetector.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.model.Device
import java.util.*
import kotlin.Comparator
import kotlin.concurrent.schedule

class DevicesListViewModel : ViewModel() {
//    val favoriteComparator = Comparator<Device> { return favoriteDevices.contains(it) }

    // TODO : REDIRECT
    val devices = mutableStateOf(listOf(Device(), Device()))
    private val favoriteDevices = mutableStateOf<Set<Device>>(setOf())

    fun share(device: Device) {
        // TODO
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
        println("${favoriteDevices.value.size} of ${devices.value.size}")
        // TODO : REMOVE
        Timer().schedule(500L) {
            devices.value = devices.value.plus(Device())
        }
    }

    fun getItinerary(device: Device) {
        // TODO
    }
}