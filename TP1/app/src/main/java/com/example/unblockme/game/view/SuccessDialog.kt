package com.example.unblockme.game.view

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.R
import com.example.unblockme.common.view.CustomDialog
import com.example.unblockme.game.viewmodel.SuccessViewModel
import androidx.compose.animation.AnimatedVisibility

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