package com.example.bluetoothdetector.common.view

import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel

// Theme selector toggle in header bar
@Composable
fun ThemeSelector(
    viewModel: ThemeSelectorViewModel = hiltViewModel()
) {
    CenteredHorizontalContainer {
        Icon(Icons.Filled.LightMode, "Light Mode")
        Switch(
            checked = viewModel.isDarkTheme.value,
            onCheckedChange = { viewModel.toggleTheme() }
        )
        Icon(Icons.Filled.DarkMode, "Dark Mode")
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun ThemeSelectorPreview() {
    ThemeSelector()
}