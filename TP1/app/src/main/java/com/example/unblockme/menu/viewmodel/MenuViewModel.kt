package com.example.unblockme.menu.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.common.view.Page
import kotlin.system.exitProcess

class MenuViewModel : ViewModel() {

    fun navigateToGame(navigateTo: (Page) -> Unit) {
        navigateTo(Page.Game)
    }

    fun navigateToAbout(navigateTo: (Page) -> Unit) {
        navigateTo(Page.About)
    }

    fun navigateToExit() {
        exitProcess(0)
    }
}