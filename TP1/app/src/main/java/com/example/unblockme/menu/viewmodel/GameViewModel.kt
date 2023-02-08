package com.example.unblockme.menu.viewmodel
// TODO Move back to com.example.unblockme.game.viewmodel

import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.unblockme.common.view.Page
import com.example.unblockme.game.domain.GameManager
import java.io.File

class GameViewModel: ViewModel() {
    val currentLevel = GameManager.currentLevel
    val currentMoveCount = GameManager.currentMoveCount

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

    fun canSelectPreviousLevel(): Boolean {
        return GameManager.canSelectPreviousLevel()
    }

    fun canSelectNextLevel(): Boolean {
        return GameManager.canSelectNextLevel()
    }

    fun selectPreviousLevel() {
        GameManager.selectPreviousLevel()
    }

    fun selectNextLevel() {
        GameManager.selectNextLevel()
    }

    fun getLevelMinimalMove(): String {
        // TODO Remove or paste code here
        return ""
    }

    fun getLevelBestScore(): String {
        // TODO Remove or paste code here
        return ""
    }

    // TODO : REMOVE
    fun addState() {
        GameManager.nextState()
    }
}