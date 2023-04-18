package com.example.bluetoothdetector.auth.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.domain.LoginRedirection
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page


@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    AuthView(
        Page.LOGIN,
        navController,
        authViewModel,
        LoginRedirection,
        { authViewModel.login(it, navController) }
    ) {
        UsernameField(authViewModel)
        PasswordField(authViewModel)
    }
}

@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.username,
        label = R.string.auth_username,
        icon = Icons.Default.Fingerprint
    ) {
        authViewModel.onUsernameChange(it)
    }
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.password,
        label = R.string.auth_password,
        icon = Icons.Default.Password
    ) {
        authViewModel.onPasswordChange(it)
    }
}
