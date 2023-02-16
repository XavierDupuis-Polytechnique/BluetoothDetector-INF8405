package com.example.unblockme.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.game.domain.GameManager

class SuccessViewModel : ViewModel() {
    ////The success window will appear
    val isSuccessShown = GameManager.isSuccessShown
}