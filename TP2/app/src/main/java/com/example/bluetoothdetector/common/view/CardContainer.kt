package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Helper card container with internal padding
@Composable
fun CardContainer(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 8.dp,
    verticalPadding: Dp = 8.dp,
    borderColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )
            .then(modifier),
        border = BorderStroke(
            width = 4.dp,
            color = borderColor
        )
    ) {
        content()
    }
}