package com.example.unblockme.game.models

data class GameState (
    val currentMoveCount: Int = 0,
    val blockCoordinates: Blocks
)