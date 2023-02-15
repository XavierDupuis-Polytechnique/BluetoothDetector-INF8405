package com.example.unblockme.game.model

import androidx.compose.ui.graphics.Color

// A list of "Block" is called "Blocks"
typealias Blocks = List<Block>

// Block direction enum
enum class Direction { Vertical, Horizontal }

// Abstract class to represent a block as
//      a list of coordinates
//      a direction (Vertical / Horizontal)
//      and a color
sealed class Block(
    open val coordinates: List<Coordinates>,
    open val direction: Direction,
    val color: Color,
) {
    fun containsCoordinate(coordinate: Coordinates): Boolean {
        return coordinates.contains(coordinate)
    }
    // Abstract method to update a block coordinates
    // Defined in subclasses
    abstract fun move(steps: Int): Block

    // Returns an updated coordinates list from a positive/negative "steps" value
    protected fun updateCoordinates(steps: Int): List<Coordinates> {
        return coordinates.map { it.copy(
            x = if (direction === Direction.Horizontal) it.x + steps else it.x,
            y = if (direction === Direction.Vertical) it.y + steps else it.y,
        )}
    }

    fun getMaxCoordinate(): Coordinates {
        var max: Coordinates = coordinates.fold(coordinates[0]) {
                max, coordinates -> maxCoordinatesComparator(coordinates, max)
        }
        return max
    }

    fun getMinCoordinate(): Coordinates {
        var min: Coordinates = coordinates.fold(coordinates[0]) {
                min, coordinates -> minCoordinatesComparator(coordinates, min)
        }
        return min
    }

    // Coordinates comparator to find the "lowest" coordinates
    // (the most "top-left" coordinate)
    private fun minCoordinatesComparator(
        coordinates: Coordinates,
        currentMin: Coordinates
    ): Coordinates {
        return if (coordinates.x < currentMin.x || coordinates.y < currentMin.y) coordinates else currentMin
    }

    // Coordinates comparator to find the "highest" coordinates
    // (the most "bottom-right" coordinate)
    private fun maxCoordinatesComparator(
        coordinates: Coordinates,
        currentMax: Coordinates
    ): Coordinates {
        return if (coordinates.x > currentMax.x || coordinates.y > currentMax.y) coordinates else currentMax
    }

}

// Subclass of Block to represent the other blocks
data class OtherBlock(
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Gray) {
    // Updates coordinates and return an updated OtherBlock
    override fun move(steps: Int): OtherBlock {
        return OtherBlock(
            updateCoordinates(steps),
            direction
        )
    }
}

// Subclass of from Block to represent the main block (red)
data class MainBlock (
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Red) {
    // Updates coordinates and return an updated MainBlock
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
){
    fun next(value: Int, direction: Direction): Coordinates{
        return if(direction == Direction.Horizontal) Coordinates(x+value,y) else Coordinates(x,y+value)
    }

    fun previous(value: Int, direction: Direction): Coordinates{
        return if(direction == Direction.Horizontal) Coordinates(x+value,y) else Coordinates(x,y+value)

    }
}