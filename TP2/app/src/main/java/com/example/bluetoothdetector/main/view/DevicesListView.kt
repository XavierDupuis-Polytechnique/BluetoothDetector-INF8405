package com.example.bluetoothdetector.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.DeviceActions
import com.example.bluetoothdetector.main.viewmodel.DevicesListViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import com.example.bluetoothdetector.ui.theme.RECORDED_DEVICES

@Composable
fun DevicesListView(
    viewModel: DevicesListViewModel = hiltViewModel()
) {
    val sortedDevices = remember(
        viewModel.devices.value,
        viewModel.favoriteDevices.value
    ) {
        viewModel.devices.value.sortedBy { !viewModel.isFavorite(it) }
    }
    CenteredVerticalContainer {
        Text("${viewModel.devices.value.size} $RECORDED_DEVICES")
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            items(sortedDevices) {
                DeviceView(
                    device = it,
                    isFavorite = viewModel.isFavorite(it),
                    isExpanded = viewModel.isExpanded(it),
                    deviceActions = DeviceActions(
                        share = { viewModel.share(it) },
                        toggleFavorite = { viewModel.toggleFavorite(it) },
                        getItinerary = { viewModel.getItinerary(it) },
                        forget = { viewModel.forget(it) },
                        expand = { viewModel.toggleExpanded(it) },
                    )
                )
                Text(viewModel.getBluetoothD())
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DeviceEmptyListPreview() {
    val viewModel: DevicesListViewModel = hiltViewModel()
    viewModel.devices.value = listOf()
    DevicesListView(viewModel)
}


@Preview(showBackground = true)
@Composable
fun DeviceListPreview() {
    DevicesListView()
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun DeviceListDarkPreview() {
    BluetoothDetectorTheme(mutableStateOf(true)) {
        DevicesListView()
    }
}