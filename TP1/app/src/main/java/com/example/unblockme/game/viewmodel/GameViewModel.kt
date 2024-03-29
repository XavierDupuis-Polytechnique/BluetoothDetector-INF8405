package com.example.unblockme.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.common.view.Page
import com.example.unblockme.game.domain.GameManager

class GameViewModel : ViewModel() {
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
}