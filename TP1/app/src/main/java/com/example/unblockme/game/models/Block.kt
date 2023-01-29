package com.example.unblockme.game.models

import androidx.compose.ui.graphics.Color

enum class Direction { Vertical, Horizontal }

sealed class Block(
    open val coordinates: List<Coordinates>,
    open val direction: Direction,
    val color: Color,){

    fun move(isIncreasing: Boolean){
        if(direction == Direction.Vertical)
            coordinates.forEach{
                it.y = if(isIncreasing) it.y + 1 else it.y - 1
            }
        else
            coordinates.forEach{
                it.x = if(isIncreasing) it.x + 1 else it.x - 1
            }
    }


    fun containsCoordinate(coordinate: Coordinates): Boolean{
        for(coord in coordinates){if(coordinate == coord) return true}
        return false
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

    private fun minCoordinatesComparator(
        coordinates: Coordinates,
        currentMin: Coordinates
    ): Coordinates {
        return if (coordinates.x < currentMin.x || coordinates.y < currentMin.y) coordinates else currentMin
    }

    private fun maxCoordinatesComparator(
        coordinates: Coordinates,
        currentMax: Coordinates
    ): Coordinates {
        return if (coordinates.x > currentMax.x || coordinates.y > currentMax.y) coordinates else currentMax
    }

}


data class OtherBlock(
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Gray)

data class MainBlock (
    override val coordinates: List<Coordinates>,
    override val direction: Direction
): Block(coordinates, direction, Color.Red)

data class Coordinates(
    var x: Int,
    var y: Int,
)