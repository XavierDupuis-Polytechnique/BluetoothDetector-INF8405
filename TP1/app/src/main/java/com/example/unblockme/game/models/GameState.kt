package com.example.unblockme.game.models

data class GameState (
    val blocks: Blocks,
    val currentMoveCount: Int = 0
)