package com.example.bluetoothdetector.common.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.main.view.MainScreen
import com.example.bluetoothdetector.splash.view.SplashScreen


@Composable
fun Navigation(
    permissionsViewModel: PermissionsViewModel,
    startDestination: Page = Page.Splash,
    navController: NavHostController = rememberNavController()
) {
    // function used to allow composable to navigate to other pages
    val navigateTo = { page: Page -> navController.navigate(page.name) }

    NavHost(navController, startDestination.name) {
        composable(Page.Splash.name) {
            Page {
                SplashScreen(navigateTo)
            }
        }

        composable(Page.Main.name) {
            PageWithHeader(
                headerContent = {
                    HeaderView(permissionsViewModel)
                }
            ) {
                MainScreen(
                    navigateTo = navigateTo,
                )
            }
        }
    }
}

