package com.example.bluetoothdetector.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeSelectorViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    // Holds the current theme value (dark = true / light = false)
    var isDarkTheme = themeRepository.isDarkTheme

    // Toggles the current theme
    fun toggleTheme() {
        themeRepository.toggleTheme()
    }
}