package com.example.unblockme.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.common.view.Page
import com.example.unblockme.game.domain.GameManager

class GameViewModel: ViewModel() {
    fun navigateToMenu(navigateTo: (Page) -> Unit) {
        navigateTo(Page.Menu)
    }

    fun canUndo(): Boolean {
        return GameManager.canPop()
    }

    fun canReset(): Boolean {
        return GameManager.canClear()
    }

    fun undo() {
        GameManager.pop()
    }

    fun reset() {
        GameManager.clear()
    }
}