package com.example.bluetoothdetector.main.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.Page
import com.example.bluetoothdetector.main.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navigateTo: (Page) -> Unit,
    viewModel: MainViewModel = viewModel(),
) {
    CenteredHorizontalContainer {
        MapView()
        DevicesListView()
    }
}