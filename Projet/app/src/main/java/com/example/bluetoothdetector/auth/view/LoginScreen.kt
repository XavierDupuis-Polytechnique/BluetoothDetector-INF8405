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
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    AuthView(
        Page.LOGIN,
        navController,
        authViewModel,
        { authViewModel.createUser(it) },
        Page.SIGNUP,
        R.string.auth_dont_have_an_account
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
        authViewModel.onUserNameChange(it)
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
