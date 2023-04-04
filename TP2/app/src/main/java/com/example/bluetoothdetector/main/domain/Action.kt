package com.example.bluetoothdetector.main.domain

import androidx.compose.ui.graphics.vector.ImageVector

// Action container
data class Action(
    val label: () -> String,
    val canAction: () -> Boolean = { true },
    val action: () -> Unit,
    val icon: () -> ImageVector,
    val actionSeverity: ActionSeverity = ActionSeverity.Normal
)