package com.example.bluetoothdetector.common.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class ThemeRepository (isDarkByDefault: Boolean = false) {
    var isDarkTheme: MutableState<Boolean> = mutableStateOf(isDarkByDefault)

    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }

    fun init(isSystemInDarkTheme: Boolean) {
        isDarkTheme.value = isSystemInDarkTheme
    }
}