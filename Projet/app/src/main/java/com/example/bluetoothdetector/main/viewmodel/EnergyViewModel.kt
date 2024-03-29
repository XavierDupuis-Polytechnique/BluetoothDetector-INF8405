package com.example.bluetoothdetector.main.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.repository.EnergyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnergyViewModel @Inject constructor(
    val energyRepository: EnergyRepository
) : ViewModel() {

    //bool to indicate if battery percentage is for app since created else since app resumed
    val isStatsSinceCreatedDisplayed = mutableStateOf(false)
    fun toggleStatsDisplayed() {
        isStatsSinceCreatedDisplayed.value = !isStatsSinceCreatedDisplayed.value
    }
}