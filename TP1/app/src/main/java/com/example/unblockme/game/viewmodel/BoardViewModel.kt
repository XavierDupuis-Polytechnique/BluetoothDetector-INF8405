package com.example.unblockme.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager

class BoardViewModel: ViewModel() {
    val currentState = GameManager.currentState
}