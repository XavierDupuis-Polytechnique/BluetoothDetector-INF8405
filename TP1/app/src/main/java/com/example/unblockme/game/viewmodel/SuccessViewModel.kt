package com.example.unblockme.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager

class SuccessViewModel:ViewModel() {
    val isSuccessShown = GameManager.isSuccessShown



    fun onDismissDialog() {
        isSuccessShown.value = false
    }

    fun selectNextLevel() {
        GameManager.selectNextLevel()
    }

}