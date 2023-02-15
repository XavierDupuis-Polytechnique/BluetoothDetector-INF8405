package com.example.unblockme.game.view

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.R
import com.example.unblockme.common.view.CustomDialog
import com.example.unblockme.game.viewmodel.SuccessViewModel

@Composable
fun SuccessDialog(
    viewModel: SuccessViewModel = viewModel()
) {
    if (viewModel.isSuccessShown.value) {
        //The success window will appear with sound
        val context = LocalContext.current
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.audio)
        mediaPlayer.start()
        CustomDialog(
            onDismiss = { viewModel.onDismissDialog() }
        )
    }
}