package com.example.bluetoothdetector.common.view.containers

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.WeightedCard(
    weight: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CardContainer(
        modifier = modifier
            .then(Modifier.weight(weight)),
    ) {
        content()
    }
}

@Composable
fun RowScope.WeightedCard(
    weight: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CardContainer(
        modifier = modifier
            .then(Modifier.weight(weight)),
    ) {
        content()
    }
}