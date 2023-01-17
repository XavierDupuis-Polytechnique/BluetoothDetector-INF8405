package com.example.unblockme.game.models

typealias Blocks = List<Block>

enum class Direction { Vertical, Horizontal }

data class Block(
    val positions: List<Position>,
    val direction: Direction
)

data class Position(
    val x: Int,
    val y: Int,
)