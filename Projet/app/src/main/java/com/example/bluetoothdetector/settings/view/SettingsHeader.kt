package com.example.bluetoothdetector.settings.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.settings.viewmodel.SettingsViewModel

@Composable
fun SettingsHeader(
    navController: NavHostController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    IconButton(onClick = { viewModel.navigate(navController) }) {
        val isOnSettingsPage = viewModel.isOnSettingsPage(navController)
        val icon = if (isOnSettingsPage)
            Icons.Default.ArrowBack
        else
            Icons.Default.Settings
        Icon(
            imageVector = icon,
            contentDescription = icon.toString()
        )
    }
}