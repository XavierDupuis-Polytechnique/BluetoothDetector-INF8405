package com.example.bluetoothdetector.common.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class Page(
    val denomination: String,
    val route: String,
    val description: String,
    val icon: ImageVector = Icons.Default.BrokenImage,
    val inMenu: Boolean = true,
) {
    SPLASH(
        denomination = "L-SPLASH",
        route = "SPLASH",
        description = "L - SPLASH DESCRIPTION",
        inMenu = false
    ),
    MAIN(
        denomination = "L-MAIN",
        route = "MAIN",
        description = "L - MAIN DESCRIPTION",
        icon = Icons.Default.TravelExplore
    ),
    MAP(
        denomination = "L-MAP",
        route = "MAP",
        description = "L - MAP DESCRIPTION",
        icon = Icons.Default.Map
    ),
    DEVICES(
        denomination = "L-DEVICES",
        route = "DEVICES",
        description = "L - DEVICES DESCRIPTION",
        icon = Icons.Default.Devices
    ),
    ACCOUNT(
        denomination = "L-ACCOUNT",
        route = "ACCOUNT",
        description = "L - ACCOUNT DESCRIPTION",
        icon = Icons.Default.AccountCircle
    ),
    ENERGY(
        denomination = "L-ENERGY",
        route = "ENERGY",
        description = "L - ENERGY DESCRIPTION",
        icon = Icons.Default.Bolt
    ),
    NETWORK(
        denomination = "L-NETWORK",
        route = "NETWORK",
        description = "L - NETWORK DESCRIPTION",
        icon = Icons.Default.SignalCellularAlt
    ),
    PERMISSIONS(
        denomination = "L-PERMISSIONS",
        route = "PERMISSIONS",
        description = "L - PERMISSIONS DESCRIPTION",
        icon = Icons.Default.SettingsApplications
    );

    companion object {
        val Pages = Page.values()
        val MenuPages = Pages.filter { it.inMenu }
    }
}