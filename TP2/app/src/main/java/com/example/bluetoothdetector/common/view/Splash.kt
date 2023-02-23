package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Splash(navigateTo: (Page) -> Unit) {
    CenteredVerticalContainer {
        Text("SplashScreen")
        SpinnerView()
    }
}