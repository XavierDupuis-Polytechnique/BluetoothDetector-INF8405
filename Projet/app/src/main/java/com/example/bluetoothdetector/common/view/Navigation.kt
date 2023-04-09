package com.example.bluetoothdetector.common.view

import androidx.compose.material.DrawerValue
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.main.view.DevicesListView
import com.example.bluetoothdetector.main.view.MainScreen
import com.example.bluetoothdetector.main.view.MapView
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

                composable(Page.SPLASH.route) {
                    SplashScreen(navController)
                }

                composable(Page.MAIN.route) {
                    MainScreen()
                }

                composable(Page.MAP.route) {
                    MapView()
                }

                composable(Page.DEVICES.route) {
                    DevicesListView()
                }

                composable(Page.ACCOUNT.route) {
                    Text("TODO Account")
                }

                composable(Page.ENERGY.route) {
                    Text("TODO Energy")
                }

                composable(Page.NETWORK.route) {
                    Text("TODO Network")
                }

                composable(Page.PERMISSIONS.route) {
                    Text("TODO Permissions")
                    //    CenteredHorizontalContainer(
                    //        modifier = Modifier
                    //            .fillMaxSize()
                    //    ) {
                    //        CenteredVerticalContainer {
                    //            PermissionHeader(permissionsViewModel)
                    //        }
                    //    }
                }
            }
        }
    }
}

