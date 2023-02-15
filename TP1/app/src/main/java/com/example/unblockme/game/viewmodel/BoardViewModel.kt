package com.example.unblockme.menu.viewmodel
// TODO Move back to com.example.unblockme.game.viewmodel

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
            if( block.containsCoordinate(targetCoordinates) && block != movingBlock) return false}
        return true
    }

    private fun areSquaresEmpty(movingBlock: Block, targetCoordinates: Coordinates, dragValue: Float, gridDivisionSize: Float): Boolean{
        var temporaryCoordinate = if(dragValue>0) movingBlock.getMaxCoordinate() else movingBlock.getMinCoordinate()
        val conv = convertPixelsToCoordinate(
            if(dragValue>0)blockMovements[movingBlock]!!.value - gridDivisionSize else blockMovements[movingBlock]!!.value + gridDivisionSize, gridDivisionSize)
        temporaryCoordinate = temporaryCoordinate.next(conv , movingBlock.direction)
        do{
            if(!isSquareEmpty(movingBlock, temporaryCoordinate))return false
            temporaryCoordinate = if(dragValue > 0) temporaryCoordinate.next(1, movingBlock.direction)
            else temporaryCoordinate.previous(-1, movingBlock.direction)
        }while(targetCoordinates != temporaryCoordinate && isInBoard(temporaryCoordinate))
        return isSquareEmpty(movingBlock,targetCoordinates)
    }

    private fun convertPixelsToCoordinate(pixel: Float, gridDivisionSize: Float): Int{
        return if(pixel>0)(floor((-1* (pixel)/gridDivisionSize).toDouble())*-1).toInt()
        else (floor(((pixel)/gridDivisionSize).toDouble())).toInt()

    }

    private fun canMove(block: Block, dragAmount: Offset, gridDivisionSize: Float):Boolean{
        var targetCoordinates: Coordinates
        val dragValue = if(block.direction == Direction.Horizontal) dragAmount.x else dragAmount.y
        if (dragValue.equals(0.0) ) return false

        targetCoordinates = if((dragValue > 0 )){
            block.getMaxCoordinate().next(convertPixelsToCoordinate((dragValue + blockMovements[block]!!.value),gridDivisionSize),block.direction)
        } else{
            val min = block.getMinCoordinate()
            min.previous(convertPixelsToCoordinate((dragValue + blockMovements[block]!!.value), gridDivisionSize),block.direction)
        }
        return isInBoard(targetCoordinates) && areSquaresEmpty(block, targetCoordinates, dragValue, gridDivisionSize)
    }

    fun move(block: Block, dragAmount: Offset, gridDivisionSize: Float) {
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
        // steps: number of squares the block moves across
        val steps = (floor((blockMovements[block]?.value!!/gridDivisionSize+0.4).toDouble())).toInt()
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