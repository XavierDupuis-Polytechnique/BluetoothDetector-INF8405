package com.example.bluetoothdetector.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeSelectorViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {
    var isDarkTheme = themeRepository.isDarkTheme
    fun toggleTheme() {
        themeRepository.toggleTheme()
    }
}