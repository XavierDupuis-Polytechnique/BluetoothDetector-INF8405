package com.example.unblockme.game.domain

import com.example.unblockme.game.models.GameState
import java.util.*

object GameManager {
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