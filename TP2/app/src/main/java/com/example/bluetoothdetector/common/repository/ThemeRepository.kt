package com.example.bluetoothdetector.common.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

// Repository for theme
class ThemeRepository(isDarkByDefault: Boolean = false) {
    // Stores the current theme value (true == dark, false == light)
    var isDarkTheme: MutableState<Boolean> = mutableStateOf(isDarkByDefault)

    // Toggles theme from current value
    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }

    // Initializes theme from current system preference
    fun init(isSystemInDarkTheme: Boolean) {
        isDarkTheme.value = isSystemInDarkTheme
    }
}