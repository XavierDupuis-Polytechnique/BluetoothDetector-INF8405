package com.example.unblockme.game.view

import android.media.MediaPlayer
import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.R
import com.example.unblockme.common.view.CustomDialog
import com.example.unblockme.game.viewmodel.SuccessViewModel

@Composable
fun SuccessDialog(
    viewModel: SuccessViewModel = viewModel()
) {
    AnimatedVisibility(
        visible = viewModel.isSuccessShown.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        CustomDialog()
    }
    if (viewModel.isSuccessShown.value) {
        val context = LocalContext.current
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.audio)
        mediaPlayer.start()
    }
}

