package com.example.unblockme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.unblockme.common.view.Navigation
import com.example.unblockme.ui.theme.UnblockMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
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
}