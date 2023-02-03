package com.example.unblockme.menu.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager

class BoardViewModel: ViewModel() {
    val currentState = GameManager.currentState
}