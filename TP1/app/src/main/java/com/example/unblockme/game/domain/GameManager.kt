package com.example.unblockme.game.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.unblockme.game.models.GameState
import java.util.*

const val FirstLevel = 1
const val LastLevel = 3

object GameManager {
    val currentLevel = mutableStateOf(FirstLevel)
    val currentMoveCount = mutableStateOf(0)
    val currentState: MutableState<GameState> = mutableStateOf(getLevelInitialState())

    private fun getLevelIndex(level: Int): Int {
        return level - 1
    }

    private fun getLevelInitialState(level: Int = currentLevel.value): GameState {
        return LevelLayouts[getLevelIndex(level)]
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

//    fun push(blocks: Blocks) {
//        push(GameState(blocks))
//    }

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
}