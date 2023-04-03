package com.example.bluetoothdetector.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.Action
import com.example.bluetoothdetector.main.domain.ActionSeverity
import com.example.bluetoothdetector.main.domain.DeviceActions
import com.example.bluetoothdetector.main.viewmodel.DevicesListViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import com.example.bluetoothdetector.ui.theme.RECORDED_DEVICES

@Composable
fun DevicesListView(
    viewModel: DevicesListViewModel = hiltViewModel()
) {
    val sortedDevices = remember(
        // TODO : FIX NEXT LINE (SHOULD BE "viewModel.devices.size" BUT DOESN'T WORK)
        viewModel.devices.size,
        viewModel.favoriteDevices.value,
        viewModel.highlightedDevice.value
    ) {
        viewModel.devices.values
            .sortedBy { it.date }
            .sortedBy { !viewModel.isFavorite(it) }
            .sortedBy { !viewModel.isHighlighted(it) }
    }
    CenteredVerticalContainer {
        CenteredHorizontalContainer {
            Text("${viewModel.deviceCount.collectAsState(initial = 0).value} $RECORDED_DEVICES")
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            DeviceButton(
                button = Action(
                    action = { viewModel.forgetAll() },
                    label = { "" },
                    canAction = { true },
                    icon = { Icons.Default.Delete },
                    actionSeverity = ActionSeverity.Danger
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            // TODO REMOVE
            DeviceButton(
                button = Action(
                    action = { viewModel.discoverDevice() },
                    label = { "" },
                    canAction = { true },
                    icon = { Icons.Default.PlusOne },
                    actionSeverity = ActionSeverity.Normal
                )
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(sortedDevices) {
                DeviceView(
                    device = it,
                    isFavorite = viewModel.isFavorite(it),
                    isHighlighted = viewModel.isHighlighted(it),
                    isExpanded = viewModel.isExpanded(it),
                    deviceActions = DeviceActions(
                        share = { viewModel.share(it) },
                        toggleFavorite = { viewModel.toggleFavorite(it) },
                        getItinerary = { viewModel.getItinerary(it) },
                        forget = { viewModel.forget(it) },
                        expand = { viewModel.toggleExpanded(it) },
                    )
                )
            }
        }
    }
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