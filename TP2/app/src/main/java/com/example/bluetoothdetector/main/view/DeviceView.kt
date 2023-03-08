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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.view.CardContainer
import com.example.bluetoothdetector.common.view.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.ActionButton
import com.example.bluetoothdetector.main.domain.DeviceActions
import com.example.bluetoothdetector.main.domain.Severity
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.main.model.testBluetooth
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme

@Composable
fun DeviceView(
    device: Device,
    isFavorite: Boolean,
    isExpanded: Boolean,
    deviceActions: DeviceActions,
) {
    CardContainer(
        modifier = Modifier
            .clickable { deviceActions.expand() },
        borderColor = if (isFavorite)
            MaterialTheme.colors.secondary
        else
            MaterialTheme.colors.primary
    ) {
        CenteredVerticalContainer(
            modifier = Modifier
                .padding(10.dp)
        ) {
            DeviceInfo(device)
            if (isExpanded) {
                DeviceButtons(deviceActions, isFavorite)
            }
        }
    }
}

@Composable
private fun DeviceInfo(device: Device) {
    CenteredVerticalContainer {
        Text(device.name)
        Text(device.macAddress)
        Text(device.date.toString())
        Text(testBluetooth(LocalContext.current))
    }
}

@Composable
private fun DeviceButtons(deviceActions: DeviceActions, isFavorite: Boolean) {
    CenteredHorizontalContainer {
        DeviceButton(
            button = ActionButton(
                action = deviceActions.share,
                label = { "" },
                canAction = { true },
                icon = { Icons.Default.Share }
            )
        )
        DeviceButton(
            button = ActionButton(
                action = deviceActions.toggleFavorite,
                label = { "" },
                canAction = { true },
                icon = { if (isFavorite) Icons.Default.Star else Icons.Default.Stars }
            )
        )
        DeviceButton(
            button = ActionButton(
                action = deviceActions.getItinerary,
                label = { "" },
                canAction = { true },
                icon = { Icons.Default.Map }
            )
        )
        DeviceButton(
            button = ActionButton(
                action = deviceActions.forget,
                label = { "" },
                canAction = { true },
                icon = { Icons.Default.Delete },
                severity = Severity.Danger
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
    DeviceView(
        device = Device(),
        isFavorite = isFavorite,
        isExpanded = isExpanded,
        DeviceActions(
            share = {},
            toggleFavorite = { isFavorite = !isFavorite },
            getItinerary = {},
            forget = {},
            expand = { isExpanded = !isExpanded }
        )
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