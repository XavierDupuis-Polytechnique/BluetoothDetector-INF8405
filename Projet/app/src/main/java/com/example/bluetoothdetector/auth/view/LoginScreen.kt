package com.example.bluetoothdetector.auth.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page


@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    AuthView(
        "*LOGIN",
        navController,
        authViewModel,
        { authViewModel.createUser(it) },
        Page.SIGNUP,
        "*Don't have an account?"
    ) {
        UsernameField(authViewModel)
        PasswordField(authViewModel)
    }
}

@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(
        authViewModel.authState.userName
    ) {
        authViewModel.onUserNameChange(it)
    }
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField(
        authViewModel.authState.password
    ) {
        authViewModel.onPasswordChange(it)
    }
}
