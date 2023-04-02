package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.MapsModule
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.viewmodel.MapViewModel
import com.example.bluetoothdetector.ui.theme.deviceMarker
import com.example.bluetoothdetector.ui.theme.favoriteDeviceMarker
import com.example.bluetoothdetector.ui.theme.highlightedDeviceMarker
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapView(
    viewModel: MapViewModel = hiltViewModel()
) {
    CenteredVerticalContainer {
        val permissionsState =
            rememberMultiplePermissionsState(MapsModule.permissions)

        val anyLocationPermissionGranted =
            viewModel.areAnyRequiredPermissionsGranted(permissionsState)

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(viewModel.getStartPosition(), 18f)
        }

        val mapStyle =
            if (viewModel.isDarkTheme.value)
                R.raw.night_map_style
            else
                R.raw.retro_map_style

        val properties = MapProperties(
            isMyLocationEnabled = anyLocationPermissionGranted,
            isBuildingEnabled = true,
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
            DeviceMarkers(viewModel)
        }
    }
}

@Composable
private fun DeviceMarkers(viewModel: MapViewModel) {
    viewModel.devices.values.forEach { device ->
        device.location?.let { location ->
            val color =
                if (viewModel.isHighlighted(device))
                    MaterialTheme.colors.highlightedDeviceMarker
                else if (viewModel.isFavorite(device))
                    MaterialTheme.colors.favoriteDeviceMarker
                else
                    MaterialTheme.colors.deviceMarker
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
