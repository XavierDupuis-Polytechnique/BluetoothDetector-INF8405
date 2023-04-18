package com.example.bluetoothdetector.auth.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.domain.AccountRedirection
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.auth.viewmodel.removeEmail
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.domain.formatter.formatDate
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.google.firebase.auth.FirebaseUser
import java.util.*

@Composable
fun AccountScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val currentUser = authViewModel.currentUser.collectAsState(null).value
    if (currentUser == null) {
        LoginScreen(navController, authViewModel)
    } else {
        AuthView(
            Page.ACCOUNT,
            navController,
            authViewModel,
            AccountRedirection
        ) {
            WelcomeBackView(currentUser)
            LastSignInView(currentUser)
            AccountCreatedView(currentUser)
        }
    }
}

@Composable
fun WelcomeBackView(firebaseUser: FirebaseUser) {
    val username = firebaseUser.email?.removeEmail() ?: ""
    Subtitle(stringResource(R.string.auth_welcome_back, username))
}

@Composable
fun LastSignInView(firebaseUser: FirebaseUser) {
    val date = firebaseUser.metadata?.lastSignInTimestamp?.let { Date(it).formatDate() } ?: ""
    Text(stringResource(R.string.auth_last_sign_in, date))
}

@Composable
fun AccountCreatedView(firebaseUser: FirebaseUser) {
    val date = firebaseUser.metadata?.creationTimestamp?.let { Date(it).formatDate() } ?: ""
    Text(stringResource(R.string.auth_account_created, date))
}
