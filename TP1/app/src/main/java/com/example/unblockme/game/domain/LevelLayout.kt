package com.example.unblockme.game.domain

import com.example.unblockme.game.models.*


val Level1Layout: Blocks = listOf(
    OtherBlock(
        listOf(
            Coordinates(0, 0),
            Coordinates(1, 0),
            Coordinates(2, 0),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(2, 1),
            Coordinates(2, 2),
            Coordinates(2, 3),
        ),
        Direction.Vertical
    ),
    MainBlock(
        listOf(
            Coordinates(0, 2),
            Coordinates(1, 2),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(0, 3),
            Coordinates(0, 4),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(0, 5),
            Coordinates(1, 5),
            Coordinates(2, 5),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(5, 0),
            Coordinates(5, 1),
            Coordinates(5, 2),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(4, 3),
            Coordinates(5, 3),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(4, 4),
            Coordinates(4, 5),
        ),
        Direction.Vertical
    )
)

val Level2Layout: Blocks = listOf(
    // TODO
    OtherBlock(
        listOf(
            Coordinates(3, 0),
            Coordinates(3, 1),
            Coordinates(3, 2),
        ),
        Direction.Vertical
    ),
)

val Level3Layout: Blocks = listOf(
    // TODO
    OtherBlock(
        listOf(
            Coordinates(4, 0),
            Coordinates(4, 1),
            Coordinates(4, 2),
        ),
        Direction.Vertical
    ),
)

val LevelLayouts = listOf(Level1Layout, Level2Layout, Level3Layout)
