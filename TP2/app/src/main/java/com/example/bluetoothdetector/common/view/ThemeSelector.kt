package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel

@Composable
fun ThemeSelector(
    viewModel: ThemeSelectorViewModel = viewModel()
) {
    CenteredHorizontalContainer{
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