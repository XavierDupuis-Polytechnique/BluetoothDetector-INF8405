package com.example.bluetoothdetector.common.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bluetoothdetector.R

enum class Page(
    val denomination: Int,
    val route: String,
    val description: Int,
    val icon: ImageVector = Icons.Default.BrokenImage,
    val inMenu: Boolean = true,
) {
    SPLASH(
        denomination = R.string.splash_page_name,
        route = "SPLASH",
        description = R.string.splash_page_description,
        inMenu = false
    ),
    MAIN(
        denomination = R.string.main_page_name,
        route = "MAIN",
        description = R.string.main_page_description,
        icon = Icons.Default.TravelExplore
    ),
    MAP(
        denomination = R.string.map_page_name,
        route = "MAP",
        description = R.string.map_page_description,
        icon = Icons.Default.Map
    ),
    DEVICES(
        denomination = R.string.devices_page_name,
        route = "DEVICES",
        description = R.string.devices_page_description,
        icon = Icons.Default.Devices
    ),
    ACCOUNT(
        denomination = R.string.account_page_name,
        route = "ACCOUNT",
        description = R.string.account_page_description,
        icon = Icons.Default.AccountCircle
    ),
    ENERGY(
        denomination = R.string.energy_page_name,
        route = "ENERGY",
        description = R.string.energy_page_description,
        icon = Icons.Default.Bolt
    ),
    NETWORK(
        denomination = R.string.network_page_name,
        route = "NETWORK",
        description = R.string.network_page_description,
        icon = Icons.Default.SignalCellularAlt
    ),
    PERMISSIONS(
        denomination = R.string.permissions_page_name,
        route = "PERMISSIONS",
        description = R.string.permissions_page_description,
        icon = Icons.Default.SettingsApplications
    ),
    SIGNUP(
        denomination = R.string.auth_signup,
        route = "SIGNUP",
        description = R.string.auth_signup_description,
        icon = Icons.Default.PersonAdd,
        inMenu = false
    ),
    LOGIN(
        denomination = R.string.auth_login,
        route = "LOGIN",
        description = R.string.auth_login_description,
        icon = Icons.Default.Login,
        inMenu = false
    );

    companion object {
        val Pages = Page.values()
        val MenuPages = Pages.filter { it.inMenu }
        val StartPage = SPLASH
        val LoggedInPage = MAIN
        val LoggedOutPage = LOGIN
    }
}