package com.example.bluetoothdetector.main.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    val networkRepository: NetworkRepository
) : ViewModel() {

    val isStatsSinceCreatedDisplayed = mutableStateOf(false)

    fun toggleStatsDisplayed() {
        isStatsSinceCreatedDisplayed.value = !isStatsSinceCreatedDisplayed.value
    }

    init {
        refresh()
    }

    fun refresh() {
        networkRepository.refresh()
    }


}