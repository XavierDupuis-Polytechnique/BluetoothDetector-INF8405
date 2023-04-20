package com.example.bluetoothdetector.common.view

import androidx.compose.material.DrawerValue
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothdetector.auth.view.AccountScreen
import com.example.bluetoothdetector.auth.view.LoginScreen
import com.example.bluetoothdetector.auth.view.SignupScreen
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.view.page.ContentPage
import com.example.bluetoothdetector.common.view.page.PageWithHeader
import com.example.bluetoothdetector.common.view.permissions.PermissionsView
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.main.view.DevicesListView
import com.example.bluetoothdetector.main.view.MainScreen
import com.example.bluetoothdetector.main.view.MapView
import com.example.bluetoothdetector.main.view.NetworkView
import com.example.bluetoothdetector.menu.view.MenuDrawer
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import com.example.bluetoothdetector.splash.view.SplashScreen


@Composable
fun Navigation(
    permissionsViewModel: PermissionsViewModel,
    navController: NavHostController = rememberNavController()
) {
    val menuState = rememberDrawerState(DrawerValue.Closed)
    val menuScope = rememberCoroutineScope()
    val authViewModel: AuthViewModel = hiltViewModel()
    val menuViewModel: MenuViewModel = viewModel()
    val navigate: (Page) -> Unit = {
        menuViewModel.navigate(navController, menuState, menuScope, it)
    }

    PageWithHeader(menuState, menuScope, menuViewModel) {
        MenuDrawer(menuState, navigate, menuViewModel) {
            NavHost(navController, Page.StartPage.route) {
                pageComposable(Page.SPLASH) {
                    SplashScreen(navigate)
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

                pageComposable(Page.LOGIN) {
                    LoginScreen(navigate, authViewModel)
                }

                pageComposable(Page.SIGNUP) {
                    SignupScreen(navigate, authViewModel)
                }

                pageComposable(Page.ACCOUNT) {
                    AccountScreen(navigate, authViewModel)
                }

                pageComposable(Page.ENERGY) {
                    Text("TODO Energy")
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

