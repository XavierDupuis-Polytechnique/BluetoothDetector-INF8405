package com.example.unblockme.game.models

import androidx.compose.ui.graphics.Color

typealias Blocks = List<Block>

enum class Direction { Vertical, Horizontal }

sealed class Block(
    open val coordinates: List<Coordinates>,
    open val direction: Direction,
    val color: Color,
)

data class OtherBlock(
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Gray)

data class MainBlock (
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Red)

data class Coordinates(
    val x: Int,
    val y: Int,
)