package com.example.bluetoothdetector.auth.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page

@Composable
fun AccountScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    if (!authViewModel.hasUser) {
        authViewModel.navigate(navController, Page.LOGIN)
    } else {
        AuthView(
            Page.ACCOUNT,
            navController,
            authViewModel,
            { authViewModel.logout() },
            Page.LOGIN,
            R.string.auth_change_account
        ) {
            UsernameField(authViewModel)
            PasswordField(authViewModel)
        }
    }
}

@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.username,
        label = R.string.auth_username,
        icon = Icons.Default.Fingerprint
    )
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.username,
        label = R.string.auth_hidden_password,
        icon = Icons.Default.Password
    )
}