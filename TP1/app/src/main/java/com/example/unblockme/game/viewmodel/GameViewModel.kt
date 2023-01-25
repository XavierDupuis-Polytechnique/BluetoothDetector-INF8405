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

    fun getCurrentRecord(): String {
        return bestScores[GameManager.getCurrentLevel() - 1]
    }

    fun getCurrentMin(): String {
        val minScores = mutableListOf("15", "17", "15")
        return minScores[GameManager.getCurrentLevel() - 1]
    }

    @Composable
    fun saveToCache(level: Int,  score: Int) {
        // TODO Call on game win
        val filename = "cache"
        val context = LocalContext.current
        val path = context.filesDir
        val file = File(path, filename)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("--\n--\n--")
        }
        var oldScores = file.readLines().toMutableList()
        oldScores[level] = score.toString()
        var newScores: String = ""
        for (score in oldScores) {
            newScores += score + "\n"
        }
        file.writeText(newScores)
    }

    var bestScores = mutableListOf<String>("--", "--", "--")

    @Composable
    fun readCache() {
        // TODO Call on game initial load
        val filename = "cache"
        val context = LocalContext.current
        val path = context.filesDir
        val file = File(path, filename)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("--\n--\n--")
        }
        val currentScores = file.readLines().toMutableList()
        bestScores = currentScores


    }

}