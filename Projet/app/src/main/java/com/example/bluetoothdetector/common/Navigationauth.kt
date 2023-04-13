package com.example.bluetoothdetector.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothdetector.home.Home
import com.example.bluetoothdetector.login.LoginScreen
import com.example.bluetoothdetector.login.LoginViewModel
import com.example.bluetoothdetector.login.SignUpScreen


enum class LoginRoutes{
    Singup,
    SignIn
}

enum class HomeRoutes{
    Home,
    Detail
}

@Composable
fun Navigation(
    navController: NavController = rememberNavController(),
    loginViewModel: LoginViewModel
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = LoginRoutes.SignIn.name
    ){
        composable(route = LoginRoutes.SignIn.name){
            LoginScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.Home.name){
                    launchSingleTop= true
                    popUpTo(route = LoginRoutes.SignIn.name){
                        inclusive = true
                    }
                }
            },
                LoginViewModel = loginViewModel


            ) {
                navController.navigate(LoginRoutes.Singup.name){
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name){
                        inclusive = true
                    }
                }
            }
        }

        composable(route = LoginRoutes.Singup.name){
            SignUpScreen(onNavToHomePage = {navController.navigate(HomeRoutes.Home.name){
                popUpTo(LoginRoutes.Singup.name){
                    inclusive = true
                }
            }
            },
                LoginViewModel = loginViewModel
            ) {
                navController.navigate(LoginRoutes.SignIn.name)
            }
        }

        composable(route = HomeRoutes.Home.name){
            Home()
        }








    }

}