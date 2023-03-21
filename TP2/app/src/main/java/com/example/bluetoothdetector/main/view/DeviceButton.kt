package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.Action
import com.example.bluetoothdetector.main.domain.ActionSeverity

@Composable
fun DeviceButton(
    button: Action
) {
    Button(
        modifier = Modifier.padding(2.dp),
        onClick = button.action,
        enabled = button.canAction(),
        colors =
        if (button.actionSeverity == ActionSeverity.Danger)
            ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.error,
                contentColor = MaterialTheme.colors.onError,
            )
        else
            ButtonDefaults.buttonColors(),
    ) {
        CenteredVerticalContainer {
            Icon(
                imageVector = button.icon(),
                contentDescription = button.label()
            )
        }
    }
}