package com.example.bluetoothdetector.auth.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.R

@Composable
fun SignupScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    AuthView(
        Page.SIGNUP,
        navController,
        authViewModel,
        { authViewModel.signup(it) },
        Page.LOGIN,
        R.string.auth_already_have_an_account
    ) {
        UsernameField(authViewModel)
        PasswordField(authViewModel)
        ConfirmPasswordField(authViewModel)
    }
}

@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.userNameSignUp,
        label = R.string.auth_username,
        icon = Icons.Default.Fingerprint
    ) {
        authViewModel.onUserNameChangeSignup(it)
    }
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.passwordSignUp,
        label = R.string.auth_password,
        icon = Icons.Default.Password
    ) {
        authViewModel.onPasswordChangeSignup(it)
    }
}

@Composable
private fun ConfirmPasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.confirmPasswordSignUp,
        label = R.string.auth_confirm_password,
        icon = Icons.Default.Password
    ) {
        authViewModel.onConfirmPasswordChange(it)
    }
}

