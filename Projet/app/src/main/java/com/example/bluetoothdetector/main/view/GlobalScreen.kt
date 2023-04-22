package com.example.bluetoothdetector.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme

const val RightWeight = 1f
const val LeftWeight = RightWeight * 2

@Composable
fun GlobalScreen() {
    CenteredHorizontalContainer(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CardContainer(
            modifier = Modifier
                .weight(LeftWeight)
                .fillMaxHeight()
        ) {
            MapView()
        }
        CardContainer(
            modifier = Modifier
                .weight(RightWeight)
                .fillMaxHeight()
        ) {
            DevicesListView()
        }
    }
}

@SuppressLint("VisibleForTests")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun GlobalPreview() {
    GlobalScreen()
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun GlobalDarkPreview() {
    BluetoothDetectorTheme(mutableStateOf(true)) {
        GlobalPreview()
    }
}