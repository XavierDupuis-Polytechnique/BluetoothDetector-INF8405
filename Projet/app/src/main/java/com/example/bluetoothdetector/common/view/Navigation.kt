package com.example.bluetoothdetector.common.view

import androidx.compose.material.DrawerValue
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.view.page.ContentPage
import com.example.bluetoothdetector.common.view.page.PageWithHeader
import com.example.bluetoothdetector.common.view.permissions.PermissionsView
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.main.view.*
import com.example.bluetoothdetector.menu.view.MenuDrawer
import com.example.bluetoothdetector.splash.view.SplashScreen


@Composable
fun Navigation(
    permissionsViewModel: PermissionsViewModel,
    startDestination: Page = Page.SPLASH,
    navController: NavHostController = rememberNavController()
) {
    val menuState = rememberDrawerState(DrawerValue.Closed)
    val menuScope = rememberCoroutineScope()

    PageWithHeader(menuState, menuScope) {
        MenuDrawer(menuState, menuScope, navController) {
            NavHost(navController, startDestination.route) {
                pageComposable(Page.SPLASH) {
                    SplashScreen(navController)
                }

                pageComposable(Page.MAIN) {
                    MainScreen()
                }

                pageComposable(Page.MAP) {
                    MapView()
                }

                pageComposable(Page.DEVICES) {
                    DevicesListView()
                }

                pageComposable(Page.ACCOUNT) {
                    Text("TODO Account")
                }

                pageComposable(Page.ENERGY) {
                    EnergyView()
                }

                pageComposable(Page.NETWORK) {
                    NetworkView()
                }

                pageComposable(Page.PERMISSIONS) {
                    PermissionsView(permissionsViewModel)
                }
            }
        }
    }
}

private fun NavGraphBuilder.pageComposable(
    page: Page,
    content: @Composable () -> Unit,
) {
    composable(page.route) {
        ContentPage {
            content()
        }
    }
}

