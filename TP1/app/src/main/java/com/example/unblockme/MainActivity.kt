package com.example.unblockme

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unblockme.common.view.Navigation
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.ui.theme.UnblockMeTheme
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        thread {
            GameManager.readCache()
        }
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
            MainScreen(viewModel = viewModel)
            playAudio(this)

    }
}

@Composable
fun playAudio(context: Context){

    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.audio)


}

@Composable
fun MainContent() {
    UnblockMeTheme { 
        Navigation()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MainContentPreview() {
    MainContent()
}}