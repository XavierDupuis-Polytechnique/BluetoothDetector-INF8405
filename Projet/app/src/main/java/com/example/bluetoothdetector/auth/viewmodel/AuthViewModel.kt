package com.example.bluetoothdetector.auth.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.model.AuthState
import com.example.bluetoothdetector.auth.repository.AccountRepository
import com.example.bluetoothdetector.common.domain.Page
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {
    // Holds the current logged in user
    val currentUser = accountRepository.currentUser

    // Holds the current authentication state
    var authState by mutableStateOf(AuthState())
        private set

    // Holds the current profile picture URI
    val profilePictureUri: MutableState<Uri?> = mutableStateOf(null)

    // Clears auth state and profile picture URI
    private fun clearState() {
        authState = AuthState()
        profilePictureUri.value = null
    }

    // Updates the username field in AuthState
    fun onUsernameChange(username: String) {
        authState = authState.copy(username = username)
    }

    // Updates the password field in AuthState
    fun onPasswordChange(password: String) {
        authState = authState.copy(password = password)
    }

    // Updates the usernameSignup field in AuthState
    fun onUsernameSignupChange(usernameSignup: String) {
        authState = authState.copy(usernameSignup = usernameSignup)
    }

    // Updates the passwordSignUp field in AuthState
    fun onPasswordSignupChange(passwordSignUp: String) {
        authState = authState.copy(passwordSignUp = passwordSignUp)
    }

    // Updates the confirmPassword field in AuthState
    fun onConfirmPasswordChange(confirmPassword: String) {
        authState = authState.copy(confirmPassword = confirmPassword)
    }

    // Validates the Login form fields from specified rules
    private fun validateLoginForm(context: Context): String? {
        if (authState.username.isEmpty() || authState.username.isBlank()) {
            return context.getString(R.string.auth_username_content_error)
        }
        if (authState.password.isEmpty() || authState.password.isBlank()) {
            return context.getString(R.string.auth_password_content_error)
        }
        if (authState.password.length < MinPasswordLength) {
            return context.getString(R.string.auth_password_size_error, MinPasswordLength)
        }
        return null
    }

    // Validates the Signup form fields from specified rules
    private fun validateSignupForm(context: Context): String? {
        if (authState.usernameSignup.isEmpty() || authState.usernameSignup.isBlank()) {
            return context.getString(R.string.auth_username_content_error)
        }
        if (authState.passwordSignUp.isEmpty() || authState.passwordSignUp.isBlank()) {
            return context.getString(R.string.auth_password_content_error)
        }
        if (authState.passwordSignUp.length < MinPasswordLength) {
            return context.getString(R.string.auth_password_size_error, MinPasswordLength)
        }
        if (authState.passwordSignUp != authState.confirmPassword) {
            return context.getString(R.string.auth_password_match_error)
        }
        if (profilePictureUri.value == null) {
            return context.getString(R.string.auth_image_content_error)
        }
        return null
    }

    // Process Signup request
    //      If request successful, try to upload profile picture
    //          If upload successful, navigate to Account page
    fun signup(
        context: Context,
        navigate: (Page) -> Unit
    ) = viewModelScope.launch {
        authState = authState.copy(isLoading = true)
        authState = authState.copy(error = null)

        val signupError = validateSignupForm(context)
        if (signupError != null) {
            authState = authState.copy(isLoading = false)
            authState = authState.copy(error = signupError)
            return@launch
        }

        executeAuthOperation {
            accountRepository.signup(
                authState.usernameSignup.appendEmail(),
                authState.passwordSignUp
            ) { isSuccess ->
                authState = authState.copy(isSuccess = isSuccess)
                if (isSuccess) {
                    profilePictureUri.value?.let {
                        uploadProfilePicture(it, navigate)
                    }
                }
            }
        }
    }

    private fun uploadProfilePicture(
        uri: Uri,
        navigate: (Page) -> Unit
    ) = viewModelScope.launch {
        accountRepository.setProfilePicture(uri, authState.usernameSignup) { imageUploaded ->
            if (imageUploaded) {
                navigate(navigate, Page.ACCOUNT)
            }
        }
    }

    // Process Login request
    //      If request successful, try to retrieve profile picture URL
    //          If retrieval successful, navigate to Account page
    fun login(
        context: Context,
        navigate: (Page) -> Unit
    ) = viewModelScope.launch {
        authState = authState.copy(isLoading = true)
        authState = authState.copy(error = null)

        val loginError = validateLoginForm(context)
        if (loginError != null) {
            authState = authState.copy(isLoading = false)
            authState = authState.copy(error = loginError)
            return@launch
        }

        executeAuthOperation {
            accountRepository.login(
                authState.username.appendEmail(),
                authState.password
            ) { isSuccess ->
                authState = authState.copy(isSuccess = isSuccess)
                if (isSuccess) {
                    navigate(navigate, Page.ACCOUNT)
                }
            }
        }
    }

    // Executes Auth request (Login/Signup) safely
    // Displays error message on failure
    private suspend fun executeAuthOperation(authOperation: suspend () -> Unit) {
        try {
            authOperation()
        } catch (exception: Exception) {
            exception.printStackTrace()
            authState = authState.copy(error = exception.localizedMessage)
            authState = authState.copy(isLoading = false)
        }
    }

    fun signOut() = viewModelScope.launch {
        accountRepository.signOut()
    }

    // Navigates to another page
    //      Navigation should clear the current auth state
    fun navigate(navigate: (Page) -> Unit, page: Page) {
        clearState()
        navigate(page)
    }

    // Retrieves the current logged in user profile picture URI
    fun getProfilePictureUri(
        currentUser: FirebaseUser,
        onComplete: (Uri?) -> Unit
    ) {
        currentUser.email?.removeEmail()?.let {
            accountRepository.getProfilePicture(it, onComplete)
        }
    }

    companion object {
        const val MinPasswordLength = 6
    }
}

// All Firebase Authentication usernames are part of the "@inf8405.com" domain
private fun String.appendEmail(): String {
    return plus("@inf8405.com")
}

fun String.removeEmail(): String {
    return removeSuffix("@inf8405.com")
}
