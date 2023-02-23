package com.example.bluetoothdetector.main.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class ActionButton(
    val label: () -> String = { "" },
    val canAction: () -> Boolean = { true },
    val action: () -> Unit = { },
    val icon: () -> ImageVector? = { null },
)