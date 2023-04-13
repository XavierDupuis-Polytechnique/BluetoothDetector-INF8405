package com.example.bluetoothdetector.auth.view

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.bluetoothdetector.R

@Composable
fun AuthField(
    value: String,
    enabled: Boolean = true,
    label: Int = R.string.no_string,
    icon: ImageVector = Icons.Default.BrokenImage,
    onValueChange: (String) -> Unit = { }
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        readOnly = !enabled,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = icon.toString()
            )
        }
    )
}