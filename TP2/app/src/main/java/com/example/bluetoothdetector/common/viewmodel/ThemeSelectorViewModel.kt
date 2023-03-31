package com.example.bluetoothdetector.common.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.repository.ThemeRepository
import com.example.bluetoothdetector.main.repository.DeviceRepository
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