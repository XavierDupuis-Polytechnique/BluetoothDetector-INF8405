package com.example.bluetoothdetector.auth.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.domain.SignupRedirection
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.view.camera.ImagePicker

@Composable
fun SignupScreen(
    navigate: (Page) -> Unit,
    authViewModel: AuthViewModel
) {
    AuthView(
        Page.SIGNUP,
        navigate,
        authViewModel,
        SignupRedirection,
        { authViewModel.signup(it, navigate) },
    ) {
        UsernameField(authViewModel)
        PasswordField(authViewModel)
        ConfirmPasswordField(authViewModel)
        PictureField(authViewModel)
    }
}

@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.usernameSignup,
        label = R.string.auth_username,
        icon = Icons.Default.Fingerprint
    ) {
        authViewModel.onUsernameSignupChange(it)
    }
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.passwordSignUp,
        label = R.string.auth_password,
        icon = Icons.Default.Password
    ) {
        authViewModel.onPasswordSignupChange(it)
    }
}

@Composable
private fun ConfirmPasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.confirmPassword,
        label = R.string.auth_confirm_password,
        icon = Icons.Default.Password
    ) {
        authViewModel.onConfirmPasswordChange(it)
    }
}

@Composable
fun PictureField(authViewModel: AuthViewModel) {
    ImagePicker(authViewModel.profilePictureUri)
}
