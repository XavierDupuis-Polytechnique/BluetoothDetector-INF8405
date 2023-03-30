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
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "currentLocation",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),

            )
            viewModel.devices.values.forEach {
                //if(it.location?.value?.latitude != null && it.location?.value?.longitude!=null)
                Marker(
                    state = MarkerState(position = LatLng(it.location!!.value!!.latitude,it.location!!.value!!.longitude)),
                    title = "${it.name}",
                    /*snippet = "Marker in Singapore"*///text under the title when click on marker
                )
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