package com.example.bluetoothdetector.main.view

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.action.Action
import com.example.bluetoothdetector.common.domain.action.ActionSeverity
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.DeviceActions
import com.example.bluetoothdetector.main.model.Device
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import com.example.bluetoothdetector.ui.theme.defaultDevice
import com.example.bluetoothdetector.ui.theme.favoriteDevice
import com.example.bluetoothdetector.ui.theme.highlightedDevice
import java.util.*

// Devices information and actions
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
            // Expand / Collapse device on click
            .clickable { deviceActions.expand() },
        borderColor = borderColor
    ) {
        CenteredVerticalContainer(
            modifier = Modifier
                .padding(10.dp)
        ) {
            DeviceInfo(device)
            // Only show extended information and actions while expanded
            if (isExpanded) {
                DeviceAdditionalInfo(device)
                val isLocationAvailable = device.location !== null
                DeviceButtons(deviceActions, isFavorite, isLocationAvailable)
            }
        }
    }
}

// Collapsed device information
@Composable
private fun DeviceInfo(device: Device) {
    CenteredVerticalContainer {
        DeviceField(device.name)
        DeviceField(Device.formatDate(device))
    }
}

// Expanded device information
@Composable
fun DeviceAdditionalInfo(device: Device) {
    CenteredVerticalContainer {
        DeviceField(device.macAddress, stringResource(R.string.device_mac_address))
        device.location?.let {
            DeviceField(Device.formatLocation(it.latitude), stringResource(R.string.latitude))
            DeviceField(Device.formatLocation(it.longitude), stringResource(R.string.longitude))
        }
        device.bluetoothClass?.let { DeviceField(it, stringResource(R.string.device_class)) }
        device.type?.let { DeviceField(it, stringResource(R.string.device_type)) }
    }
}

// Device action buttons
@Composable
private fun DeviceButtons(
    deviceActions: DeviceActions,
    isFavorite: Boolean,
    isLocationAvailable: Boolean
) {
    CenteredHorizontalContainer {
        DeviceButton(
            button = Action(
                execute = deviceActions.share,
                icon = { Icons.Default.Share }
            )
        )
        DeviceButton(
            button = Action(
                execute = deviceActions.toggleFavorite,
                icon = { if (isFavorite) Icons.Default.Star else Icons.Default.Stars }
            )
        )
        DeviceButton(
            button = Action(
                execute = deviceActions.getItinerary,
                canExecute = { isLocationAvailable },
                icon = { Icons.Default.Map }
            )
        )
        DeviceButton(
            button = Action(
                execute = deviceActions.forget,
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
        device = Device(
            name = "MyDevice",
            macAddress = "12:23:34:45:67:AB",
            date = Date(),
            bluetoothClass = "AUDIO_VIDEO_CAMCORDER",
            type = "DEVICE_TYPE_UNKNOWN",
            bondState = "BOND_NONE",
            location = Location("1").apply {
                latitude = 12.345678912345656
                longitude = 67.345678912345656
            }
        ),
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