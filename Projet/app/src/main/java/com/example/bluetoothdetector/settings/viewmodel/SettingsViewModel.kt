package com.example.bluetoothdetector.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.common.view.Page

class SettingsViewModel : ViewModel() {
    fun isOnSettingsPage(navController: NavHostController): Boolean {
        return navController.currentBackStackEntry?.destination?.route === Page.Settings.name
    }

    fun navigate(navController: NavHostController) {
        if (isOnSettingsPage(navController)) {
            navController.popBackStack()
        } else {
            navController.navigate(Page.Settings.name)
        }
    }
}