package com.example.bluetoothdetector.main.domain

import androidx.compose.ui.graphics.vector.ImageVector

enum class Severity {
    Normal,
    Danger
}

data class ActionButton(
    val label: () -> String,
    val canAction: () -> Boolean = { true },
    val action: () -> Unit,
    val icon: () -> ImageVector,
    val severity: Severity = Severity.Normal
)