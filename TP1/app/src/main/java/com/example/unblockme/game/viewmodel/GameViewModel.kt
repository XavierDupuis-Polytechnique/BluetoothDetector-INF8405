package com.example.unblockme.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.common.view.Page

class GameViewModel: ViewModel() {
    fun navigateToMenu(navigateTo: (Page) -> Unit) {
        navigateTo(Page.Menu)
    }
}