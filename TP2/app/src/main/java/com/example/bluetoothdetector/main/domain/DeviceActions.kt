package com.example.bluetoothdetector.main.domain

data class DeviceActions(
    val share: () -> Unit,
    val toggleFavorite: () -> Unit,
    val getItinerary: () -> Unit,
)