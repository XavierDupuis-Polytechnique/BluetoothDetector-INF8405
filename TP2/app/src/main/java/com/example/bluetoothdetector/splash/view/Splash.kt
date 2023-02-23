package com.example.bluetoothdetector.splash.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.view.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.Page
import com.example.bluetoothdetector.common.view.SpinnerView
import com.example.bluetoothdetector.splash.viewmodel.SplashViewModel

const val AppVersion = 1.0
val Developers = listOf(
    "Xavier Dupuis",
    "William Lévesque",
    "Marie Noël",
    "Mohammed Imade MEDDANE"
)

@Composable
fun SplashScreen(
    navigateTo: (Page) -> Unit,
    viewModel: SplashViewModel = viewModel()
) {
    CenteredVerticalContainer {
        Text("Bluetooth Detector v${AppVersion}")
        SpinnerView()
        // TODO : LOGO
        Developers.forEach {
            Text(it)
        }
    }
    viewModel.launchDelayedNavigate(navigateTo)
}