package com.example.bluetoothdetector.common.view.page

import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer

@Composable
fun ContentPage(
    content: @Composable () -> Unit
) {
    Page {
        CenteredVerticalContainer {
            content()
        }
    }
}