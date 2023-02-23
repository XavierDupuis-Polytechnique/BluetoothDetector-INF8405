package com.example.bluetoothdetector.main.view

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.view.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.ActionButton
import com.example.bluetoothdetector.main.domain.DeviceActions
import com.example.bluetoothdetector.main.model.Device

@Composable
fun DeviceView(
    device: Device,
    isExpanded: Boolean,
    isFavorite: Boolean,
    deviceActions: DeviceActions,
) {
    CenteredVerticalContainer {
        DeviceInfo(device)
        if (isExpanded) {
            DeviceButtons(deviceActions, isFavorite)
        }
    }
}

@Composable
private fun DeviceInfo(device: Device) {
    CenteredVerticalContainer {
        Text(device.name)
        Text(device.macAddress)
        Text(device.date.toString())
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
    }
}