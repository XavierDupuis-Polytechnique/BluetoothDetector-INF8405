package com.example.unblockme.game.domain

import com.example.unblockme.game.models.*

// Level layout for level 1
val Level1Layout: Blocks = listOf(
//    OtherBlock(
//        listOf(
//            Coordinates(0, 0),
//            Coordinates(1, 0),
//            Coordinates(2, 0),
//        ),
//        Direction.Horizontal
//    ),
//    OtherBlock(
//        listOf(
//            Coordinates(2, 1),
//            Coordinates(2, 2),
//            Coordinates(2, 3),
//        ),
//        Direction.Vertical
//    ),
    MainBlock(
        listOf(
            Coordinates(0, 2),
            Coordinates(1, 2),
        ),
        Direction.Horizontal
    ),
//    OtherBlock(
//        listOf(
//            Coordinates(0, 3),
//            Coordinates(0, 4),
//        ),
//        Direction.Vertical
//    ),
//    OtherBlock(
//        listOf(
//            Coordinates(0, 5),
//            Coordinates(1, 5),
//            Coordinates(2, 5),
//        ),
//        Direction.Horizontal
//    ),
//    OtherBlock(
//        listOf(
//            Coordinates(5, 0),
//            Coordinates(5, 1),
//            Coordinates(5, 2),
//        ),
//        Direction.Vertical
//    ),
//    OtherBlock(
//        listOf(
//            Coordinates(4, 3),
//            Coordinates(5, 3),
//        ),
//        Direction.Horizontal
//    ),
//    OtherBlock(
//        listOf(
//            Coordinates(4, 4),
//            Coordinates(4, 5),
//        ),
//        Direction.Vertical
//    )
)

// Level layout for level 2
val Level2Layout: Blocks = listOf(
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
            Coordinates(1, 3),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(2, 1),
            Coordinates(2, 2),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(2, 3),
            Coordinates(2, 4),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(2, 5),
            Coordinates(3, 5),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(3, 2),
            Coordinates(3, 3),
            Coordinates(3, 4),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(4, 2),
            Coordinates(4, 3),
            Coordinates(4, 4),
        ),
        Direction.Vertical
    ),
)

// Level layout for level 3
val Level3Layout: Blocks = listOf(
    MainBlock(
        listOf(
            Coordinates(0, 2),
            Coordinates(1, 2),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(0, 0),
            Coordinates(0, 1),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(0, 4),
            Coordinates(1, 4),
            Coordinates(2, 4),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(1, 0),
            Coordinates(2, 0),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(3, 0),
            Coordinates(4, 0),
        ),
        Direction.Horizontal
    ),
    OtherBlock(
        listOf(
            Coordinates(2, 1),
            Coordinates(2, 2),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(3, 2),
            Coordinates(3, 3),
            Coordinates(3, 4),
        ),
        Direction.Vertical
    ),
    OtherBlock(
        listOf(
            Coordinates(4, 2),
            Coordinates(4, 3),
            Coordinates(4, 4),
        ),
        Direction.Vertical
    ),
)

// All level layouts
val LevelLayouts = listOf(Level1Layout, Level2Layout, Level3Layout)
