package com.example.bluetoothdetector.main.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.view.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.ActionButton

@Composable
fun DeviceButton(
    button: ActionButton
) {
    IconButton(
        onClick = button.action,
        enabled = button.canAction(),
    ) {
        CenteredVerticalContainer {
            Text(text = button.label())
            Icon(
                imageVector = button.icon()!!,
                contentDescription = button.label()
            )
        }
    }
}