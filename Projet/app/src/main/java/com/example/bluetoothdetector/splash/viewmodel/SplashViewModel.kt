package com.example.bluetoothdetector.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluetoothdetector.common.view.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


class SplashViewModel : ViewModel() {
    private val splashTime = 3000L
    fun launchDelayedNavigate(navigateTo: (Page) -> Unit) {
        Timer().schedule(splashTime) {
            viewModelScope.launch(Dispatchers.Main) {
                navigateTo(Page.Main)
            }
        }
    }
}