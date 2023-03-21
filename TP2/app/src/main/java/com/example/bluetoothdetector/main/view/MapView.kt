package com.example.bluetoothdetector.main.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.sources.LocationRepository
import com.example.bluetoothdetector.main.viewmodel.MapViewModel

@Composable
fun MapView(
    viewModel: MapViewModel = hiltViewModel()
) {
    val longitude = viewModel.location.value?.longitude.toString()
    val latitude = viewModel.location.value?.latitude.toString()
    // TODO
    CenteredVerticalContainer {
        Text("Map")
        Text("longitude : $longitude")
        Text("latitude : $latitude")
    }
}