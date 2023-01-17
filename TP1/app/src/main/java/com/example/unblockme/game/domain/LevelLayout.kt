package com.example.unblockme.game.domain

import com.example.unblockme.game.models.Block
import com.example.unblockme.game.models.Blocks
import com.example.unblockme.game.models.Direction
import com.example.unblockme.game.models.Coordinates


val Level1Layout: Blocks = listOf(
    Block(
        listOf(
            Coordinates(0, 0),
            Coordinates(1, 0),
            Coordinates(2, 0),
        ),
        Direction.Horizontal
    ),
    // TODO
)

val Level2Layout: Blocks = listOf(
    // TODO
)

val Level3Layout: Blocks = listOf(
    // TODO
)

val LevelLayouts = listOf(Level1Layout, Level2Layout, Level3Layout)
