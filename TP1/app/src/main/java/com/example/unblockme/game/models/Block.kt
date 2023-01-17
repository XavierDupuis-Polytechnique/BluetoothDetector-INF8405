package com.example.unblockme.game.models

typealias Blocks = List<Block>

enum class Direction { Vertical, Horizontal }

data class Block(
    val coordinates: List<Coordinates>,
    val direction: Direction
)

data class Coordinates(
    val x: Int,
    val y: Int,
)