package com.example.bluetoothdetector.common.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothdetector.main.view.MainView


@Composable
fun Navigation(
    startDestination: Page = Page.Splash,
    navController: NavHostController = rememberNavController()
) {
    // function used to allow composable to navigate to other pages
    val navigateTo = { page: Page -> navController.navigate(page.name) }

    NavHost(navController, startDestination.name) {
        composable(Page.Splash.name) {
            Page {
                Splash(navigateTo)
            }
        }

        composable(Page.Main.name) {
            Page {
                MainView(navigateTo)
            }
        }
    }
}

