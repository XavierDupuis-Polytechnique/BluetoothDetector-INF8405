package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.viewmodel.MapViewModel
import com.example.bluetoothdetector.ui.theme.defaultDevice
import com.example.bluetoothdetector.ui.theme.favoriteDevice
import com.example.bluetoothdetector.ui.theme.highlightedDevice
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@Composable
fun MapView(
    viewModel: MapViewModel = hiltViewModel()
) {
    CenteredVerticalContainer {
        if (viewModel.location.value !== null) {
            val currentLocation = LatLng(
                viewModel.location.value!!.latitude,
                viewModel.location.value!!.longitude
            )
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(currentLocation, 18f)
            }
            val mapStyle =
                if (viewModel.isDarkTheme.value)
                    R.raw.night_map_style
                else
                    R.raw.retro_map_style

            val properties = MapProperties(
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                    LocalContext.current,
                    mapStyle
                )
            )

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { viewModel.mapClick(it) },
                properties = properties
            ) {
                PositionMarker(viewModel, currentLocation)
                DeviceMarkers(viewModel)
            }
        } else {
            Text("Current Location Unavailable")
        }
    }
}

@Composable
private fun PositionMarker(
    viewModel: MapViewModel,
    currentLocation: LatLng
) {
    Marker(
        state = MarkerState(position = currentLocation),
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
        title = "currentLocation",
        onClick = { viewModel.currentPositionMarkerClick(currentLocation) }
    )
}

@Composable
private fun DeviceMarkers(viewModel: MapViewModel) {
    viewModel.devices.values.forEach { device ->
        device.location?.let { location ->
            val color =
                if (viewModel.isHighlighted(device))
                    MaterialTheme.colors.highlightedDevice
                else if (viewModel.isFavorite(device))
                    MaterialTheme.colors.favoriteDevice
                else
                    MaterialTheme.colors.defaultDevice
            val hue = colorToHue(color.toArgb())
            Marker(
                state = MarkerState(
                    position = LatLng(
                        location.latitude,
                        location.longitude
                    )
                ),
                title = device.name,
                icon = BitmapDescriptorFactory.defaultMarker(hue),
                onClick = { viewModel.markerClick(device) }
            )
        }
    }
}

fun colorToHue(color: Int): Float {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(
        color,
        hsl
    )
    return hsl[0]
}
