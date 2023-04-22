package com.example.bluetoothdetector.common.view.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bluetoothdetector.common.view.containers.CardContainer

// Generic Page fullscreen view
@Composable
fun ContentPage(
    content: @Composable () -> Unit
) {
    Page {
        CardContainer(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}