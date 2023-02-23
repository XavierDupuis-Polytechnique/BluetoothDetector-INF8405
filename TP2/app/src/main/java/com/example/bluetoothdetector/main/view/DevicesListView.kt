package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.clickable
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
    LazyColumn {
        items(viewModel.devices.value) {
            var isExpanded by remember {
                mutableStateOf(true)
            }
            val toggleExpanded = { isExpanded = !isExpanded }
            Card(modifier = Modifier.clickable { toggleExpanded() }) {
                DeviceView(
                    it,
                    isExpanded,
                    viewModel.isFavorite(it),
                    deviceActions = DeviceActions(
                        share = { viewModel.share(it) },
                        toggleFavorite = { viewModel.toggleFavorite(it) },
                        getItinerary = { viewModel.getItinerary(it) },
                    )
                )
            }
        }
    }
}