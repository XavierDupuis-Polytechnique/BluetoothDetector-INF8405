package com.example.unblockme.game.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.unblockme.game.models.Blocks
import com.example.unblockme.game.models.GameState
import java.util.*

const val FirstLevel = 1

object GameManager {
    private val currentLevel = mutableStateOf(FirstLevel)

    fun getCurrentLevel(): Int {
        return currentLevel.value
    }

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
}