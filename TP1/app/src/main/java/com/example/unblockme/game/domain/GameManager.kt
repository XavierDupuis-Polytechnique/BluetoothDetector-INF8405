package com.example.unblockme.game.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.unblockme.game.models.GameState
import java.util.*

object GameManager {
    val currentState: MutableState<GameState?> = mutableStateOf(null)

    private val gameStates = Stack<GameState>()

    fun clear() {
        gameStates.clear()
        // TODO : Push initial state
        // TODO : Reset move counter
    }

    fun pop() {
        if (gameStates.empty()) {
            return
        }
        gameStates.pop()
        // TODO : Decrement move counter
    }
}