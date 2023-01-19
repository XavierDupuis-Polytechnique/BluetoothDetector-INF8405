package com.example.unblockme.game.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.unblockme.game.models.Blocks
import com.example.unblockme.game.models.GameState
import java.util.*

object GameManager {
    private val currentLevel: Int = 1

    fun getLevelIndex(level: Int): Int {
        return level - 1
    }

    val currentState: MutableState<GameState> = mutableStateOf(
        GameState(
            LevelLayouts[getLevelIndex(currentLevel)]
        )
    )

    private val gameStates = Stack<GameState>()

    fun clear() {
        gameStates.clear()
        gameStates.push(
            GameState(
                LevelLayouts[getLevelIndex(currentLevel)]
            )
        )
        currentState.value = gameStates.peek()
    }

    fun pop() {
        if (gameStates.empty()) {
            return
        }
        gameStates.pop()
        currentState.value = gameStates.peek()
    }

    fun push(gameState: GameState) {
        gameStates.push(gameState)
        currentState.value = gameStates.peek()
    }

    fun push(blocks: Blocks) {
        val newMoveCount = currentState.value.currentMoveCount + 1
        gameStates.push(GameState(blocks, newMoveCount))
        currentState.value = gameStates.peek()
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
}