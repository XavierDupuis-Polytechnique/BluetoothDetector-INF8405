package com.example.unblockme.game.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager

class BoardViewModel: ViewModel() {
    val currentState = GameManager.currentState

    fun findIndex(): Int {
        return 0
    }

    fun dragStart(position: Offset){
        var index : Int = findIndex();
        currentState.value.blocks[index].move(true)
        var a = currentState.value
        println(currentState.value.blocks[index].coordinates)
        currentState.value = a
        println(position.x);
        println(position.y);
        // TODO: get position, identify coordinate, find box index,
    }
}