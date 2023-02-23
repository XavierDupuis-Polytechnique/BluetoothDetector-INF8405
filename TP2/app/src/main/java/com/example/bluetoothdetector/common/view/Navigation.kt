package com.example.bluetoothdetector.common.view

import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(
    startDestination: Page = Page.Menu,
    navController: NavHostController = rememberNavController()
) {
    // function used to allow composable to navigate to other pages
    val navigateTo = { page: Page -> navController.navigate(page.name) }

    NavHost(navController, startDestination.name) {
        composable(Page.Menu.name) {
            Page {
                // TODO : Remove me
                Card {
                    Text("Bluetooth Detector!")
                }
                //Menu(navigateTo)
            }
        }

        composable(Page.About.name) {
            Page {
                //About(navigateTo)
            }
        }

        composable(Page.Game.name) {
            Page {
                //Game(navigateTo)
            }
        }
    }
}

