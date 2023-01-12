package com.example.unblockme.common.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unblockme.menu.view.Menu

@Composable
fun Navigation(
    startDestination: Page = Page.Menu,
    navController: NavHostController = rememberNavController()
) {
    val navigateTo = { page: Page -> navController.navigate(page.name) }

    NavHost(navController, startDestination.name) {
        composable(Page.Menu.name) {
            Menu(navigateTo)
        }

        composable(Page.About.name) {
            About(navigateTo)
        }

        composable(Page.Game.name) {
            Game(navigateTo)
        }
    }
}
