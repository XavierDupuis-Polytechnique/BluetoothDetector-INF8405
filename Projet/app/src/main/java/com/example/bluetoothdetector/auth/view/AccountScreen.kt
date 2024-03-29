package com.example.bluetoothdetector.auth.view

import android.net.Uri
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.domain.AccountRedirection
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.auth.viewmodel.removeEmail
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.domain.formatter.formatDate
import com.example.bluetoothdetector.common.view.camera.AsyncImageViewOrSpinner
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.google.firebase.auth.FirebaseUser
import java.util.*

// Displays the current logged in user information
@Composable
fun AccountScreen(
    navigate: (Page) -> Unit,
    authViewModel: AuthViewModel
) {
    authViewModel.currentUser.collectAsState(null).value?.let {
        AuthView(
            Page.ACCOUNT,
            navigate,
            authViewModel,
            AccountRedirection
        ) {
            WelcomeBackView(it)
            ProfilePictureView(authViewModel, it)
            LastSignInView(it)
            AccountCreatedView(it)
        }
    }
}

// Displays the current logged in profile picture
@Composable
fun ProfilePictureView(
    authViewModel: AuthViewModel,
    currentUser: FirebaseUser
) {
    var uri by remember {
        mutableStateOf<Uri?>(null)
    }
    authViewModel.getProfilePictureUri(currentUser) {
        uri = it
    }
    AsyncImageViewOrSpinner(uri)
}

// Displays the welcome back message
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
