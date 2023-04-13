package com.example.bluetoothdetector.auth.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.auth.model.AuthState
import com.example.bluetoothdetector.auth.repository.AuthRepository
import com.example.bluetoothdetector.common.domain.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
):ViewModel() {
    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    var authState by mutableStateOf(AuthState())
        private set

    fun onUserNameChange(userName: String){
        authState = authState.copy(userName = userName)
    }
    fun onPasswordChange(password: String){
        authState = authState.copy(password = password)
    }
    fun onUserNameChangeSignup(userName: String){
        authState = authState.copy(userNameSignUp = userName)
    }
    fun onPasswordChangeSignup(password: String){
        authState = authState.copy(passwordSignUp = password)
    }
    fun onConfirmPasswordChange(password: String){
        authState = authState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm() =
        authState.userName.isNotBlank() &&
                authState.password.isNotBlank()

    private fun validateSignupForm() =
        authState.userNameSignUp.isNotBlank() &&
                authState.passwordSignUp.isNotBlank() &&
                authState.confirmPasswordSignUp.isNotBlank()



    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignupForm()){
                throw java.lang.IllegalArgumentException("email and password can not be empty")
            }
            authState = authState.copy(isLoading = true)
            if (authState.passwordSignUp !=
                    authState.confirmPasswordSignUp){
                throw IllegalArgumentException(
                    "Password do not match"
                )
            }
            authState = authState.copy(signUpError = null)
            repository.createUser(
                authState.userNameSignUp,
                authState.passwordSignUp
            ){ isSuccessful ->
                authState = if (isSuccessful) {
                    Toast.makeText(context, "success Login",Toast.LENGTH_LONG).show()
                    authState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(context, "Failed Login",Toast.LENGTH_LONG).show()
                    authState.copy(isSuccessLogin = false)
                }
            }



        }catch (e:Exception){
            authState = authState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            authState = authState.copy(isLoading = false)
        }
    }
    fun loginUser(context: Context)= viewModelScope.launch {
        try {
            if (!validateLoginForm()){
                throw java.lang.IllegalArgumentException("email and password can not be empty")
            }
            authState = authState.copy(isLoading = true)
            authState = authState.copy(loginError = null)
            repository.login(
                authState.userName,
                authState.password
            ){ isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(context, "success Login",Toast.LENGTH_LONG).show()
                    authState = authState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(context, "Failed Login",Toast.LENGTH_LONG).show()
                    authState = authState.copy(isSuccessLogin = false)
                }
            }



        }catch (e:Exception){
            authState = authState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            authState = authState.copy(isLoading = false)
        }
    }
    fun logout() {
        TODO("Not yet implemented")
    }

    fun navigate(navController: NavHostController, page: Page) {
        navController.navigate(page.route)
    }
}
