package com.example.bluetoothdetector.auth.view

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
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
import java.util.Date

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
            AccountRedirection
        ) {
            authViewModel.currentUser?.let {
                WelcomeBackView(it)
                LastSignInView(it)
                AccountCreatedView(it)
            }
        }
    }
}

@Composable
fun WelcomeBackView(firebaseUser: FirebaseUser) {
    Subtitle("Welcome back ${firebaseUser.email?.removeEmail()} !")
}

@Composable
fun LastSignInView(firebaseUser: FirebaseUser) {
    val date = firebaseUser.metadata?.lastSignInTimestamp?.let { Date(it).formatDate() }
    Text("Last sign in : $date")
}

@Composable
fun AccountCreatedView(firebaseUser: FirebaseUser) {
    val date = firebaseUser.metadata?.creationTimestamp?.let { Date(it).formatDate() }
    Text("Account created : $date")
}
