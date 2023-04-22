package com.example.bluetoothdetector.main.viewmodel

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.repository.EnergyRepository
import com.example.bluetoothdetector.main.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnergyViewModel @Inject constructor(
    val energyRepository: EnergyRepository
) : ViewModel() {

    val isStatsSinceCreatedDisplayed = mutableStateOf(false)
    fun toggleStatsDisplayed() {
        isStatsSinceCreatedDisplayed.value = !isStatsSinceCreatedDisplayed.value
    }


    /* val isStatsSinceCreatedDisplayed = mutableStateOf(false)

     fun toggleStatsDisplayed() {
         isStatsSinceCreatedDisplayed.value = !isStatsSinceCreatedDisplayed.value
     }

     init {
         refresh()
     }

     fun refresh() {
         networkRepository.refresh()
     }*/



}