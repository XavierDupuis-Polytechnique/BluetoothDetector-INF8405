package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.view.CenteredVerticalContainer
import com.example.bluetoothdetector.main.domain.ActionButton

@Composable
fun DeviceButton(
    button: ActionButton
) {
    Button(
        modifier = Modifier.padding(2.dp),
        onClick = button.action,
        enabled = button.canAction(),
    ) {
        CenteredVerticalContainer {
            Icon(
                imageVector = button.icon(),
                contentDescription = button.label()
            )
        }
    }
}