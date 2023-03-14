package com.example.bluetoothdetector.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.CardContainer
import com.example.bluetoothdetector.common.view.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.Page
import com.example.bluetoothdetector.main.sources.LocationSource
import com.example.bluetoothdetector.main.viewmodel.MainViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun MainScreen(
    locationSource: LocationSource,
    viewModel: MainViewModel = viewModel(),
    navigateTo: (Page) -> Unit,
) {
    CenteredHorizontalContainer(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CardContainer(
            // TODO : PROBABLY UPDATE WITH
            // modifier = Modifier
            //     .wrapContentWidth()
            //     .wrapContentHeight()
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .fillMaxHeight(1f)
        ) {
            MapView(locationSource)
        }
        CardContainer(
            modifier = Modifier
                .wrapContentWidth()
        ) {
            DevicesListView()
        }
    }
}

@SuppressLint("VisibleForTests")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun MainPreview() {
    MainScreen(
        LocationSource(
            FusedLocationProviderClient(LocalContext.current)
        )
    ) {}
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun MainDarkPreview() {
    BluetoothDetectorTheme(mutableStateOf(true)) {
        MainPreview()
    }
}