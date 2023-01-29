package com.example.unblockme.game.models

import androidx.compose.ui.graphics.Color

typealias Blocks = List<Block>

enum class Direction { Vertical, Horizontal }

sealed class Block(
    open val coordinates: List<Coordinates>,
    open val direction: Direction,
    val color: Color,
) {
    fun containsCoordinate(coordinate: Coordinates): Boolean {
        return coordinates.contains(coordinate)
    }
    abstract fun move(steps: Int): Block
    protected fun updateCoordinates(steps: Int): List<Coordinates> {
        return coordinates.map { it.copy(
            x = if (direction === Direction.Horizontal) it.x + steps else it.x,
            y = if (direction === Direction.Vertical) it.y + steps else it.y,
        )}
    }
}

data class OtherBlock(
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Gray) {
    override fun move(steps: Int): OtherBlock {
        return OtherBlock(
            updateCoordinates(steps),
            direction
        )
    }
}

data class MainBlock (
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Red) {
    override fun move(steps: Int): MainBlock {
        return MainBlock(
            updateCoordinates(steps),
            direction
        )
    }
}

data class Coordinates(
    val x: Int,
    val y: Int,
)