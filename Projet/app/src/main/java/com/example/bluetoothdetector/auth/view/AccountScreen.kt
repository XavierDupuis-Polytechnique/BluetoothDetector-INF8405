package com.example.bluetoothdetector.auth.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page

@Composable
fun AccountScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    if (!authViewModel.hasUser) {
        authViewModel.navigate(navController, Page.LOGIN)
    }
    AuthView(
        "*ACCOUNT",
        navController,
        authViewModel,
        { authViewModel.logout() },
        Page.LOGIN,
        "*Want to change account?"
    ) {
        UsernameField(authViewModel)
        PasswordField(authViewModel)
    }
}

@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(authViewModel.authState.userName) { }
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField("********") { }
}