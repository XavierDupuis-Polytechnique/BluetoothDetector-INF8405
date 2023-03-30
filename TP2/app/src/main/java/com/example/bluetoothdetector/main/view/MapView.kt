package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.viewmodel.MapViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    viewModel: MapViewModel = hiltViewModel()
) {
    CenteredVerticalContainer {
        // TODO
        val currentLocation = LatLng(viewModel.location.value!!.latitude,viewModel.location.value!!.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(currentLocation, 18f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { viewModel.mapClick(it) }
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "currentLocation",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),

            )

            viewModel.devices.values.forEach { device ->
                device.location?.let { location ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                location.latitude,
                                location.longitude
                            )
                        ),
                        title = device.name,
                        onClick = { viewModel.markerClick(device) }
                    )
                }
            }
        }

        // TODO : REMOVE NEXT LINES
        val longitude = viewModel.location.value?.longitude.toString()
        val latitude = viewModel.location.value?.latitude.toString()
        Text("Map")
        Text("longitude : $longitude")
        Text("latitude : $latitude")
    }
}