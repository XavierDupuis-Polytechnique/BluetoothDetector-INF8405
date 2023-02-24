package com.example.bluetoothdetector.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.CardContainer
import com.example.bluetoothdetector.common.view.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.Page
import com.example.bluetoothdetector.main.viewmodel.MainViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme

@Composable
fun MainScreen(
    navigateTo: (Page) -> Unit,
    viewModel: MainViewModel = viewModel(),
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
            MapView()
        }
        CardContainer(
            modifier = Modifier
                .wrapContentWidth()
        ) {
            DevicesListView()
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun MainPreview() {
    MainScreen({})
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun MainDarkPreview() {
    BluetoothDetectorTheme(mutableStateOf(true)) {
        MainScreen({})
    }
}