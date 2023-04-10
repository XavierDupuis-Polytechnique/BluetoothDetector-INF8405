package com.example.bluetoothdetector.common.view.typography

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Subtitle(
    subtitle: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = subtitle,
        fontStyle = MaterialTheme.typography.h1.fontStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = color,
        modifier = modifier.then(Modifier.padding(5.dp))
    )
}