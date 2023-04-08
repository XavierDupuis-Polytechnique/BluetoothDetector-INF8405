package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.languages.LanguagesHeader
import com.example.bluetoothdetector.common.view.permissions.PermissionHeader
import com.example.bluetoothdetector.settings.view.SettingsHeader

// Header container
@Composable
fun HeaderView(
    navController: NavHostController,
) {
    CenteredHorizontalContainer(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        SettingsHeader(navController)
        Spacer(modifier = Modifier.weight(1f))
        ThemeSelector()
        Spacer(modifier = Modifier.weight(1f))
        LanguagesHeader()
    }
}