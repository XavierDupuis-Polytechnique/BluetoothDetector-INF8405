package com.example.bluetoothdetector.auth.view

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun AuthField(
    value: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = !enabled
    )
}