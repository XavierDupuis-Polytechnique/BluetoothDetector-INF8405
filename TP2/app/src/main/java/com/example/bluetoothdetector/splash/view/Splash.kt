package com.example.bluetoothdetector.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.*
import com.example.bluetoothdetector.splash.viewmodel.SplashViewModel

const val AppVersion = 1.0
const val Logo = R.drawable.icon
val LogoSize = 36.dp
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
    CardContainer {
        CenteredVerticalContainer(
            modifier = Modifier.padding(12.dp)
        ) {
            CenteredHorizontalContainer {
                Text("Bluetooth Detector v${AppVersion}")
                Spacer(modifier = Modifier.size(LogoSize.div(2)))
                Image(
                    painter = painterResource(Logo),
                    contentDescription = Logo.toString(),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.size(LogoSize)
                )
            }
            SpinnerView()
            Developers.forEach {
                Text(it)
            }
        }
    }
    viewModel.launchDelayedNavigate(navigateTo)
}