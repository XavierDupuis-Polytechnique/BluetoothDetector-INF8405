package com.example.unblockme.game.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.game.models.Block
import com.example.unblockme.game.models.Blocks
import com.example.unblockme.game.models.Coordinates
import com.example.unblockme.game.models.Direction
import com.example.unblockme.game.view.BoardDimension
import kotlin.math.floor

class BoardViewModel: ViewModel() {
    val currentState = GameManager.currentState

    val blockMovements: MutableMap<Block, MutableState<Float>> = mutableMapOf()

    fun getMovement(block: Block): MutableState<Float> {
        if (blockMovements[block] === null){
            blockMovements[block] = mutableStateOf(0f)
        }
        return blockMovements[block]!!
    }

    private fun isInBoard(targetCoordinates: Coordinates): Boolean{
        return targetCoordinates.x in 0 until BoardDimension && targetCoordinates.y in 0 until BoardDimension
    }

    private fun isSquareEmpty(movingBlock: Block, targetCoordinates: Coordinates): Boolean{
        currentState.value.forEach{ block->
            //TODO: modifier pour si le block contient la coordonnee mais qu'il
            // TODO: s'agit du même block l'espace est dispo!!!!

                if( block.containsCoordinate(targetCoordinates) && block != movingBlock) return false}
        return true

    }

    fun canMove(block: Block, dragAmount: Offset, gridDivisionSize: Float):Boolean{
        var targetCoordinates: Coordinates
        val dragValue = if(block.direction == Direction.Horizontal) dragAmount.x else dragAmount.y
        targetCoordinates = if(dragValue > 0){
            block.getMaxCoordinate().next((floor((-1* dragValue/gridDivisionSize).toDouble())*-1).toInt(),block.direction)
        } else{
            block.getMinCoordinate().previous((floor((dragValue/gridDivisionSize).toDouble())).toInt(),block.direction)
        }
        println("target: $targetCoordinates")
        return isInBoard(targetCoordinates) && isSquareEmpty(block, targetCoordinates)
    }

    fun move(block: Block, dragAmount: Offset, gridDivisionSize: Float) {
        // TODO : Wall/OtherBlocks detection (before updating block's offset)
        if(!canMove(block, dragAmount, gridDivisionSize)) return

        if (blockMovements[block] === null) {
            blockMovements[block] = mutableStateOf(0f)
        }
        val previousOffset = blockMovements[block]!!
        val newOffset = previousOffset.value +
                if (block.direction === Direction.Horizontal)
                    dragAmount.x
                else
                    dragAmount.y
        blockMovements[block]?.let {
            it.value = newOffset
            // TODO : REMOVE (DEBUG)
            println("move ${it.value}")
        }
    }

    fun getBlock(position: Coordinates): Block? {
        return currentState.value.find { it.containsCoordinate(position) }
    }

    fun release(block: Block, gridDivisionSize: Float) {
        val newState = stickBlockOnGrid(block, gridDivisionSize)
        if (newState !== null) {
            GameManager.push(newState)
        }
    }

    private fun stickBlockOnGrid(block: Block, gridDivisionSize: Float ): Blocks? {
        // TODO : Find closest column/row to fit with movement

        val steps = (floor((blockMovements[block]?.value!!/gridDivisionSize+0.4).toDouble())).toInt()
        println("steps: $steps")
        // if steps == 0 -> block did not move but is returned to fixed position in board
        if (steps == 0) {
            blockMovements[block]?.value = 0f
            return null
        }

        // Reset specific block offset
        blockMovements[block]?.value = 0f

        // Update block coordinates
        return createNewState(block, steps)
    }

    private fun createNewState(block: Block, steps: Int): MutableList<Block>? {
        val blockIndex = currentState.value.indexOf(block)
        if (blockIndex == -1) {
            println("Error -> createNewState -> COULD NOT FIND OLD BLOCK")
            return null
        }
        val oldBlock = currentState.value[blockIndex]
        val newBlock = oldBlock.move(steps)
        val newState = currentState.value.toMutableList()
        newState[blockIndex] = newBlock
        return newState
    }
}