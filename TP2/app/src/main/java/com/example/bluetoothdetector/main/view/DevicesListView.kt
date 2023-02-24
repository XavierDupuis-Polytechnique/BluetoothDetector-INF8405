package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.main.domain.DeviceActions
import com.example.bluetoothdetector.main.viewmodel.DevicesListViewModel

@Composable
fun DevicesListView(
    viewModel: DevicesListViewModel = viewModel()
) {
    // TODO : SORT BY FAVORITES
    // val sortedDevices = remember(viewModel.devices.value) {
    //     viewModel.devices.value.sortedBy { !viewModel.isFavorite(it) }
    // }
    LazyColumn(
        modifier = Modifier.fillMaxHeight(0.5f)
    ) {
        items(viewModel.devices.value) {
            DeviceView(
                it,
                viewModel.isFavorite(it),
                deviceActions = DeviceActions(
                    share = { viewModel.share(it) },
                    toggleFavorite = { viewModel.toggleFavorite(it) },
                    getItinerary = { viewModel.getItinerary(it) },
                    forget = { viewModel.forget(it) },
                )
            )
        }
    }
}