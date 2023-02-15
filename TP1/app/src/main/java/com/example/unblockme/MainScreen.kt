package com.example.unblockme

import android.media.MediaPlayer
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.sourceInformationMarkerStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.ui.theme.Purple700
import com.example.unblockme.ui.theme.Teal200






@Composable
fun MainScreen(
    viewModel: MainViewModel
){

    if (viewModel.isSuccessShown.value){
        val context = LocalContext.current
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.audio)
        mediaPlayer.start()
        CustomDialog(onDismiss = {
            viewModel.onDismissDialog()
        },
            onConfim = {

                viewModel.onDismissDialog()
                viewModel.selectNextLevel()

            }


            )


    }
}