package com.example.unblockme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager

class MainViewModel:ViewModel() {
    val isSuccessShown = GameManager.isSuccessShown



    fun onDismissDialog() {
        isSuccessShown.value = false
    }

    fun selectNextLevel() {
        GameManager.selectNextLevel()
    }

}