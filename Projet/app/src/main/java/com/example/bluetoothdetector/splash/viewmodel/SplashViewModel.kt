package com.example.bluetoothdetector.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.common.domain.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


class SplashViewModel : ViewModel() {
    private val splashTime = 3000L
    fun launchDelayedNavigate(navController: NavHostController) {
        Timer().schedule(splashTime) {
            viewModelScope.launch(Dispatchers.Main) {
                navController.navigate(Page.MAIN.route)
            }
        }
    }
}