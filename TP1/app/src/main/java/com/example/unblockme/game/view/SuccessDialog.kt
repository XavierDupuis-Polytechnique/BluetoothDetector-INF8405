package com.example.unblockme.game.view

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.R
import com.example.unblockme.common.view.CustomDialog
import com.example.unblockme.game.viewmodel.SuccessViewModel

@OptIn(ExperimentalAnimationApi::class)
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
        // The success window will appear with sound
        val context = LocalContext.current
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.audio)
        mediaPlayer.start()
    }
}