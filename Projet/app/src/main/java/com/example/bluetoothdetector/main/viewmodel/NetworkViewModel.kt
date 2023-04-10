package com.example.bluetoothdetector.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.main.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    val networkRepository: NetworkRepository
) : ViewModel() {

    init {
        refresh()
    }

    fun refresh() {
        networkRepository.refresh()
    }
}