package com.example.bluetoothdetector.common.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeSelectorViewModel(isDarkByDefault: Boolean) : ViewModel() {
    var isDarkTheme: MutableState<Boolean> = mutableStateOf(isDarkByDefault)

    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }
}