package com.example.bluetoothdetector.main.domain

// Device actions
data class DeviceActions(
    val share: () -> Unit,
    val toggleFavorite: () -> Unit,
    val getItinerary: () -> Unit,
    val forget: () -> Unit,
    val expand: () -> Unit,
)