package com.example.unblockme.game.domain

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.unblockme.game.models.Blocks
import com.example.unblockme.game.models.GameState
import java.io.File
import java.util.*

const val FirstLevel = 1
const val defaultCacheValue = "--\n--\n--"
@SuppressLint("SdCardPath")
const val defaultPath = "/data/user/0/com.example.unblockme/files"

object GameManager {
    private val currentLevel = mutableStateOf(FirstLevel)

    fun getLevelIndex(level: Int): Int {
        return level - 1
    }

    val currentMoveCount = mutableStateOf(0)
    val currentState: MutableState<GameState> = mutableStateOf(
        GameState(
            LevelLayouts[getLevelIndex(currentLevel.value)]
        )
    )

    private val gameStates = Stack<GameState>()

    init {
        readCache()
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
        gameStates.push(
            GameState(
                LevelLayouts[getLevelIndex(currentLevel.value)]
            )
        )
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
        // TODO
        return false
    }

    fun canSelectPreviousLevel(): Boolean {
        // TODO
        return false
    }

    fun selectNextLevel() {
        if(!canSelectNextLevel()) {
            return
        }
        // TODO
    }

    fun selectPreviousLevel() {
        if(!canSelectPreviousLevel()) {
            return
        }
        // TODO
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
        val filename = "cache"
        val file = File(defaultPath, filename)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText(defaultCacheValue)
        }
        var oldScores = file.readLines().toMutableList()
        oldScores[level] = score.toString()
        var newScores: String = ""
        for (score in oldScores) {
            newScores += score + "\n"
        }
        file.writeText(newScores)
    }

    fun readCache() {
        val filename = "cache"
        val file = File(defaultPath, filename)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText(defaultCacheValue)
        }
        val currentScores = file.readLines().toMutableList()
        bestScores = currentScores
    }
}