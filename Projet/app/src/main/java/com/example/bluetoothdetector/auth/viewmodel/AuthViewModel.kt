package com.example.bluetoothdetector.auth.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
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
    val currentUser = accountRepository.currentUser

    val hasUser = accountRepository.hasUser

    var authState by mutableStateOf(AuthState())
        private set

    val profilePictureUri: MutableState<Uri?> = mutableStateOf(null)

    fun onUsernameChange(username: String) {
        authState = authState.copy(username = username)
    }

    fun onPasswordChange(password: String) {
        authState = authState.copy(password = password)
    }

    fun onUsernameSignupChange(usernameSignup: String) {
        authState = authState.copy(usernameSignup = usernameSignup)
    }

    fun onPasswordSignupChange(passwordSignUp: String) {
        authState = authState.copy(passwordSignUp = passwordSignUp)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        authState = authState.copy(confirmPassword = confirmPassword)
    }

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
        return null
    }

    fun signup(
        context: Context,
        navController: NavHostController
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
                        viewModelScope.launch {
                            accountRepository.setProfilePicture(it, authState.usernameSignup, null)
                        }
                    }
                    authState = AuthState()
                    navigate(navController, Page.ACCOUNT)
                }
            }
        }
    }

    fun login(
        context: Context,
        navController: NavHostController
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
                    authState = AuthState()
                    navigate(navController, Page.ACCOUNT)
                }
            }
        }
    }

    private suspend fun executeAuthOperation(authOperation: suspend () -> Unit) {
        try {
            authOperation()
        } catch (exception: Exception) {
            authState = authState.copy(error = exception.localizedMessage)
            exception.printStackTrace()
        } finally {
            authState = authState.copy(isLoading = false)
        }
    }

    fun signOut() = viewModelScope.launch {
        accountRepository.signOut()
    }

    fun navigate(navController: NavHostController, page: Page) {
        navController.navigate(page.route)
    }

    fun getProfilePictureUri(
        currentUser: FirebaseUser,
        context: Context,
        onComplete: (Uri?) -> Unit
    ) {
        currentUser.email?.removeEmail()?.let {
            accountRepository.getProfilePicture(it, context, onComplete)
        }
    }

    companion object {
        const val MinPasswordLength = 6
    }
}

private fun String.appendEmail(): String {
    return plus("@inf8405.com")
}

fun String.removeEmail(): String {
    return removeSuffix("@inf8405.com")
}
