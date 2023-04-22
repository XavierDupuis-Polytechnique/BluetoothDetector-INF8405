package com.example.bluetoothdetector.auth.model

// Login or Signup state
data class AuthState(
    val username: String = "",
    val password: String = "",
    val usernameSignup: String = "",
    val passwordSignUp: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)