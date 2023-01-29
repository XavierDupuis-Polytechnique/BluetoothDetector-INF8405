package com.example.unblockme.game.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.game.models.Coordinates
import com.example.unblockme.game.view.BoardPadding
import androidx.compose.ui.unit.dp

class BoardViewModel: ViewModel() {
    val currentState = GameManager.currentState.value
    lateinit var coordinateInitial: Coordinates

    private fun findIndex(): Int {
        for (i in 0 until currentState!!.size){
           if(currentState[i].containsCoordinate(coordinateInitial)) return i
        }
        return -1
    }

    fun dragStart(coordinate: Coordinates){
        coordinateInitial = coordinate

        var index : Int = findIndex()
        //a.blocks[index].move(true)
        println(index)
        if(index<0) return
        println("avant:")
        println(currentState?.get(index)?.coordinates)
        val block = GameManager.remove(index)   //currentState.removeAt(index)
        block.move(true)
        GameManager.add(index, block)
        println("apres:")
        println(currentState?.get(index)?.coordinates)

        //println(a.blocks[index]
        // TODO: get position, identify coordinate, find box index,
    }

    fun drag(){}

    fun dragEnd(){}
}


