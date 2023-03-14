package com.example.bluetoothdetector.main.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.CenteredVerticalContainer
import com.example.bluetoothdetector.main.sources.LocationSource
import com.example.bluetoothdetector.main.viewmodel.MapViewModel

@Composable
fun MapView(
    locationSource: LocationSource,
    viewModel: MapViewModel = viewModel()
) {
    val currentLocation = locationSource.currentLocation.value
    val longitude = currentLocation?.longitude.toString()
    val latitude = currentLocation?.latitude.toString()
    // TODO
    CenteredVerticalContainer {
        Text("Map")
        Text("longitude : $longitude")
        Text("latitude : $latitude")
    }
}