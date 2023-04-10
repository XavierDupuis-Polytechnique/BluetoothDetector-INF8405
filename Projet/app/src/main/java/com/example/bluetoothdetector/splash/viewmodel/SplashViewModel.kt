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

    var navigateTask: TimerTask? = null
    fun launchDelayedNavigate(navController: NavHostController) {
        if (navigateTask == null) {
            navigateTask = Timer().schedule(splashTime) {
                viewModelScope.launch(Dispatchers.Main) {
                    navigateTask = null
                    navController.navigate(Page.MAIN.route)
                }
            }
        }
    }
}