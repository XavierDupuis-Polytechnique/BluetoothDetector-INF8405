package com.example.bluetoothdetector.common.domain.modal

import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.action.Action

data class ModalActions(
    val primary: Action? = null,
    val cancel: Action = Action(
        label = { R.string.cancel }
    )
)