package com.example.bluetoothdetector.settings.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.Page
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.permissions.PermissionHeader
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.settings.viewmodel.SettingsViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme

const val RightWeight = 1f
const val LeftWeight = RightWeight / 2

@Composable
fun SettingsScreen(
    permissionsViewModel: PermissionsViewModel,
    viewModel: SettingsViewModel = viewModel(),
) {
    CenteredHorizontalContainer(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CenteredVerticalContainer {
            PermissionHeader(permissionsViewModel)
            Text("Settings")
        }
    }
}

@SuppressLint("VisibleForTests")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun SettingsPreview() {
    SettingsScreen(
        permissionsViewModel = viewModel()
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun SettingsDarkPreview() {
    BluetoothDetectorTheme(mutableStateOf(true)) {
        SettingsPreview()
    }
}