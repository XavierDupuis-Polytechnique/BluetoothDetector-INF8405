package com.example.bluetoothdetector.common.view.typography

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        fontStyle = MaterialTheme.typography.h1.fontStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        modifier = modifier
    )
}