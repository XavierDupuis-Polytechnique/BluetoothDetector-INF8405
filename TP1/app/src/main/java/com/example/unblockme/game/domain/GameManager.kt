package com.example.unblockme.game.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.example.unblockme.game.models.Block
import java.util.*

const val FirstLevel = 1

object GameManager {
    private val currentLevel = mutableStateOf(FirstLevel)

    fun getLevelIndex(level: Int): Int {
        return level - 1
    }

    val currentMoveCount = mutableStateOf(0)
    var currentState: MutableLiveData<MutableList<Block>> = MutableLiveData<MutableList<Block>>()


    private val gameStates = Stack<List<Block>>()

    init {
        currentState.value = LevelLayouts[getLevelIndex(currentLevel.value)].toMutableList()
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
                LevelLayouts[getLevelIndex(currentLevel.value)]
        )
        currentState.value = gameStates.peek() as MutableList<Block>
        currentMoveCount.value = 0
    }

    fun canPop(): Boolean {
        return currentMoveCount.value > 0
    }

    fun remove(index: Int): Block{
        return currentState.value!!.removeAt(index)
    }

    fun add(index: Int, block: Block){
        currentState.value?.add(index, block)
        currentState.value = currentState.value
    }

    fun pop() {
        if (!canPop()) {
            return
        }
        gameStates.pop()
        currentState.value = gameStates.peek() as MutableList<Block>
        currentMoveCount.value--
    }

    fun push(gameState: List<Block>) {
        gameStates.push(gameState)
        currentState.value = gameStates.peek() as MutableList<Block>
        currentMoveCount.value++
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