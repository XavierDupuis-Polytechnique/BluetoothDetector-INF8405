package com.example.unblockme.game.viewmodel

import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.unblockme.common.view.Page
import com.example.unblockme.game.domain.GameManager
import java.io.File

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

    // TODO : REMOVE
    fun addState() {
        GameManager.nextState()
    }
}