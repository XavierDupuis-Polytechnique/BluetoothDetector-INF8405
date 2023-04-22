package com.example.bluetoothdetector.common.domain.action

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bluetoothdetector.R

// Generic Action attributes
data class Action(
    val label: () -> Int = { R.string.no_string },
    val canExecute: () -> Boolean = { true },
    val execute: () -> Unit = {},
    val icon: () -> ImageVector = { Icons.Default.BrokenImage },
    val actionSeverity: ActionSeverity = ActionSeverity.Normal
)