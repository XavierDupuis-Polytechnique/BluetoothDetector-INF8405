package com.example.bluetoothdetector.common.view.modal

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.domain.action.Action

@Composable
fun ModalButton(
    closeModal: () -> Unit,
    action: Action,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary,
) {
    Button(
        modifier = Modifier
            .padding(end = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        onClick = {
            action.execute()
            closeModal()
        },
        enabled = action.canExecute()
    ) {
        Text(stringResource(action.label()))
    }
}