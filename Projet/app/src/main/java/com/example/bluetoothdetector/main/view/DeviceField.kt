package com.example.bluetoothdetector.main.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer

// Device field name and value view
@Composable
fun DeviceField(
    deviceFieldValue: String,
    deviceFieldName: String? = null
) {
    CenteredHorizontalContainer {
        deviceFieldName?.let {
            Text("$deviceFieldName : ")
        }
        Text(
            fontWeight = FontWeight.Bold,
            text = deviceFieldValue
        )
    }
}
