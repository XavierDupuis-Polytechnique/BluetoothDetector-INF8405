package com.example.bluetoothdetector.auth.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page

@Composable
fun SignupScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    AuthView(
        "*SIGNUP",
        navController,
        authViewModel,
        { authViewModel.loginUser(it) },
        Page.LOGIN,
        "*Already have an Account?"
    ) {
        UsernameField(authViewModel)
        PasswordField(authViewModel)
        ConfirmPasswordField(authViewModel)
    }
}

@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(
        authViewModel.authState.userNameSignUp
    ) {
        authViewModel.onUserNameChangeSignup(it)
    }
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField(
        authViewModel.authState.passwordSignUp
    ) {
        authViewModel.onPasswordChangeSignup(it)
    }
}

@Composable
private fun ConfirmPasswordField(authViewModel: AuthViewModel) {
    AuthField(
        authViewModel.authState.confirmPasswordSignUp
    ) {
        authViewModel.onConfirmPasswordChange(it)
    }
}

