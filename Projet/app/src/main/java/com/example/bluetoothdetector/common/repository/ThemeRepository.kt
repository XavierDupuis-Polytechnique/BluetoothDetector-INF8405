package com.example.bluetoothdetector.common.repository

import androidx.compose.runtime.MutableState

// Repository for theme
class ThemeRepository(isDarkByDefault: Boolean = false) {
    // Stores the current theme value (true == dark, false == light)
    lateinit var isDarkTheme: MutableState<Boolean>

    // Toggles theme from current value
    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }
}