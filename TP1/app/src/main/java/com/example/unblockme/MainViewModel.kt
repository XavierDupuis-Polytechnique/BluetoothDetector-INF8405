package com.example.unblockme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var isDialogShown by mutableStateOf(false)
        private set

    fun onBuyClick(){
        isDialogShown = true
    }

    fun onDismissDialog(){
        isDialogShown = false
    }

}