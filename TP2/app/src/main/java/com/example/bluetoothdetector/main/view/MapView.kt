package com.example.bluetoothdetector.main.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.viewmodel.MapViewModel

@Composable
fun MapView(
    viewModel: MapViewModel = hiltViewModel()
) {
    val longitude = viewModel.location.value?.longitude.toString()
    val latitude = viewModel.location.value?.latitude.toString()
    // TODO
    CenteredVerticalContainer {
        GoogleMap()
        Text("Map")
        Text("longitude : $longitude")
        Text("latitude : $latitude")
    }
}

@Composable()
fun GoogleMap()
/*modifier: Modifier = Modifier,
              cameraPositionState: CameraPositionState = rememberCameraPositionState(),
              contentDescription: String? = null,
              googleMapOptionsFactory: () -> GoogleMapOptions = { GoogleMapOptions() },
              properties: MapProperties = DefaultMapProperties,
              locationSource: LocationSource? = null,
              uiSettings: MapUiSettings = DefaultMapUiSettings,
              indoorStateChangeListener: IndoorStateChangeListener = DefaultIndoorStateChangeListener,
              onMapClick: (LatLng) -> Unit = {},
              onMapLongClick: (LatLng) -> Unit = {},
              onMapLoaded: () -> Unit = {},
              onMyLocationButtonClick: () -> Boolean = { false },
              onMyLocationClick: (Location) -> Unit = {},
              onPOIClick: (PointOfInterest) -> Unit = {},
              contentPadding: PaddingValues = NoPadding,
              content: @Composable() () -> Unit? = null)*/
{

}