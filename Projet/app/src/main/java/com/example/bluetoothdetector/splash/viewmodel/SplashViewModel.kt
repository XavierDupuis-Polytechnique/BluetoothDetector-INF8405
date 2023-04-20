package com.example.bluetoothdetector.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluetoothdetector.auth.repository.AccountRepository
import com.example.bluetoothdetector.common.domain.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val splashTime = 3000L
    private var navigateTask: TimerTask? = null
    fun launchDelayedNavigate(navigate: (Page) -> Unit) {
        if (navigateTask == null) {
            navigateTask = Timer().schedule(splashTime) {
                viewModelScope.launch(Dispatchers.Main) {
                    navigateTask = null
                    val isLoggedIn = accountRepository.hasUser
                    val page = if (isLoggedIn) Page.LoggedInPage else Page.LoggedOutPage
                    navigate(page)
                }
            }
        }
    }
}