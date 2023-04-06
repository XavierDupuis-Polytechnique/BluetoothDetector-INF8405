package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.domain.action.Action
import com.example.bluetoothdetector.common.domain.action.ActionSeverity
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer

// Device action button
@Composable
fun DeviceButton(
    button: Action
) {
    Button(
        modifier = Modifier.padding(2.dp),
        onClick = button.execute,
        enabled = button.canExecute(),
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
                contentDescription = stringResource(button.label())
            )
        }
    }
}