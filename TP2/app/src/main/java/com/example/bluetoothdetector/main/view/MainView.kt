package com.example.bluetoothdetector.main.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.Page
import com.example.bluetoothdetector.main.viewmodel.MainViewModel

@Composable
fun MainView(
    navigateTo: (Page) -> Unit,
    viewModel: MainViewModel = viewModel()
) {

}