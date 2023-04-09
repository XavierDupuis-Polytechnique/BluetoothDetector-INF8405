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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.BuildConfig
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.SpinnerView
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.splash.viewmodel.SplashViewModel

const val AppVersion = BuildConfig.VERSION_NAME
const val Logo = R.drawable.icon
val LogoSize = 36.dp
val Developers = listOf(
    "Xavier Dupuis",
    "William Lévesque",
    "Marie Noël",
    "Mohammed Imade MEDDANE"
)


// Splash screen
@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = viewModel()
) {
    CardContainer {
        CenteredVerticalContainer(
            modifier = Modifier.padding(12.dp)
        ) {
            CenteredHorizontalContainer {
                Text("${stringResource(R.string.app_name)} v${AppVersion}")
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
    viewModel.launchDelayedNavigate(navController)
}