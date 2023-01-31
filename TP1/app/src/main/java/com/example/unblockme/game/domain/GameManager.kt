package com.example.unblockme.game.domain

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.example.unblockme.game.models.Blocks
import com.example.unblockme.game.models.GameState
import java.io.File
import java.util.*

const val FirstLevel = 1
const val defaultCacheValue = "--\n--\n--"
@SuppressLint("SdCardPath")
const val defaultPath = "/data/user/0/com.example.unblockme/files/cache"
const val LastLevel = 3

object GameManager {
    val currentLevel = mutableStateOf(FirstLevel)
    val currentMoveCount = mutableStateOf(0)
    val currentState: MutableState<GameState> = mutableStateOf(getLevelInitialState())

    private fun getLevelIndex(level: Int): Int {
        return level - 1
    }

    private fun getLevelInitialState(level: Int = currentLevel.value): GameState {
        return GameState(LevelLayouts[getLevelIndex(level)])
    }

    private val gameStates = Stack<GameState>()

    init {
        gameStates.push(currentState.value)
    }

    fun canClear(): Boolean {
        return currentMoveCount.value > 0
    }

    fun clear() {
        if (!canClear()) {
            return
        }
        gameStates.clear()
        gameStates.push(getLevelInitialState())
        currentState.value = gameStates.peek()
        currentMoveCount.value = 0
    }

    fun canPop(): Boolean {
        return currentMoveCount.value > 0
    }

    fun pop() {
        if (!canPop()) {
            return
        }
        gameStates.pop()
        currentState.value = gameStates.peek()
        currentMoveCount.value--
    }

    fun push(gameState: GameState) {
        gameStates.push(gameState)
        currentState.value = gameStates.peek()
        currentMoveCount.value++
    }

    fun push(blocks: Blocks) {
        push(GameState(blocks))
    }

    fun canSelectNextLevel(): Boolean {
        return currentLevel.value < LastLevel
    }

    fun canSelectPreviousLevel(): Boolean {
        return currentLevel.value > FirstLevel
    }

    fun selectNextLevel() {
        if(!canSelectNextLevel()) {
            return
        }
        currentLevel.value++
        setCurrentLevel()
    }

    fun selectPreviousLevel() {
        if(!canSelectPreviousLevel()) {
            return
        }
        currentLevel.value--
        setCurrentLevel()
    }

    private fun setCurrentLevel(level: Int = currentLevel.value) {
        if (level !in FirstLevel..LastLevel) {
            return
        }
        clear()
        gameStates.push(getLevelInitialState())
        currentState.value = gameStates.peek()
    }

    // TODO : REMOVE
    fun nextState() {
        push(level1Progresses[currentMoveCount.value+1])
    }

    var bestScores = mutableListOf("--", "--", "--")

    fun getCurrentBestScore(): String {
        return bestScores[currentLevel.value - 1]
    }

    fun getCurrentMin(): String {
        val minScores = mutableListOf("15", "17", "15")
        return minScores[currentLevel.value - 1]
    }

    fun saveToCache(level: Int,  score: Int) {
        // TODO Call on game win
        var file = getCache()
        var oldScores = file.readLines().toMutableList()
        oldScores[level] = score.toString()
        var newScores: String = ""
        for (score in oldScores) {
            newScores += score + "\n"
        }
        file.writeText(newScores)
        readCache()
    }

    fun readCache() {
        var file = getCache()
        val currentScores = file.readLines().toMutableList()
        bestScores = currentScores
    }

    fun resetCache() {
        var file = getCache()
        file.writeText(defaultCacheValue)
        readCache()
    }

    fun setBestCache() {
        var file = getCache()
        file.writeText("15\n17\n15")
        readCache()
    }

    fun getCache(): File {
        val file = File(defaultPath)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText(defaultCacheValue)
        }
        return file
    }
}