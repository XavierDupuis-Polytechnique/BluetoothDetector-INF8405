package com.example.bluetoothdetector.auth.domain

import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page

// Auth redirection
data class Redirection(
    val page: Page,
    val message: Int,
    val label: Int = page.description,
    val action: ((AuthViewModel) -> Unit)? = null
)

// Redirection for the login page to signup page
val LoginRedirection = Redirection(
    page = Page.SIGNUP,
    message = R.string.auth_dont_have_an_account
)

// Redirection for the signup page to login page
val SignupRedirection = Redirection(
    page = Page.LOGIN,
    message = R.string.auth_already_have_an_account,
)

// Redirection for the account page to login page
val AccountRedirection = Redirection(
    page = Page.LOGIN,
    message = R.string.auth_change_account,
    action = { it.signOut() },
)