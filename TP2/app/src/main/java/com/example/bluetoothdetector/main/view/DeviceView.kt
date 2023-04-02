package com.example.bluetoothdetector.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.Action
import com.example.bluetoothdetector.main.domain.ActionSeverity
import com.example.bluetoothdetector.main.domain.DeviceActions
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import com.example.bluetoothdetector.ui.theme.defaultDevice
import com.example.bluetoothdetector.ui.theme.favoriteDevice
import com.example.bluetoothdetector.ui.theme.highlightedDevice

@Composable
fun DeviceView(
    device: Device,
    isFavorite: Boolean,
    isHighlighted: Boolean,
    isExpanded: Boolean,
    deviceActions: DeviceActions,
) {
    val borderColor =
        if (isHighlighted) MaterialTheme.colors.highlightedDevice
        else if (isFavorite) MaterialTheme.colors.favoriteDevice
        else MaterialTheme.colors.defaultDevice

    CardContainer(
        modifier = Modifier
            .clickable { deviceActions.expand() },
        borderColor = borderColor
    ) {
        CenteredVerticalContainer(
            modifier = Modifier
                .padding(10.dp)
        ) {
            DeviceInfo(device)
            if (isExpanded) {
                DeviceAdditionalInfo(device)
                DeviceButtons(deviceActions, isFavorite)
            }
        }
    }
}

@Composable
private fun DeviceInfo(device: Device) {
    CenteredVerticalContainer {
        Text(device.name)
        Text(Device.formatDate(device))
    }
}

@Composable
fun DeviceAdditionalInfo(device: Device) {
    CenteredVerticalContainer {
        Text(device.macAddress)
        device.location?.let {
            Text(it.latitude.toString())
            Text(it.longitude.toString())
        }
        device.bluetoothClass?.let { Text(it) }
        device.type?.let { Text(it) }
    }
}

@Composable
private fun DeviceButtons(deviceActions: DeviceActions, isFavorite: Boolean) {
    CenteredHorizontalContainer {
        DeviceButton(
            button = Action(
                action = deviceActions.share,
                label = { "" },
                canAction = { true },
                icon = { Icons.Default.Share }
            )
        )
        DeviceButton(
            button = Action(
                action = deviceActions.toggleFavorite,
                label = { "" },
                canAction = { true },
                icon = { if (isFavorite) Icons.Default.Star else Icons.Default.Stars }
            )
        )
        DeviceButton(
            button = Action(
                action = deviceActions.getItinerary,
                label = { "" },
                canAction = { true },
                icon = { Icons.Default.Map }
            )
        )
        DeviceButton(
            button = Action(
                action = deviceActions.forget,
                label = { "" },
                canAction = { true },
                icon = { Icons.Default.Delete },
                actionSeverity = ActionSeverity.Danger
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DevicePreview() {
    var isFavorite by remember {
        mutableStateOf(true)
    }
    var isExpanded by remember {
        mutableStateOf(true)
    }
    val isHighlighted by remember {
        mutableStateOf(false)
    }
    DeviceView(
        device = Device(),
        isFavorite = isFavorite,
        isHighlighted = isHighlighted,
        isExpanded = isExpanded,
        DeviceActions(
            share = {},
            toggleFavorite = { isFavorite = !isFavorite },
            getItinerary = {},
            forget = {},
            expand = { isExpanded = !isExpanded }
        ),
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun DeviceDarkPreview() {
    BluetoothDetectorTheme(mutableStateOf(true)) {
        DevicePreview()
    }
}