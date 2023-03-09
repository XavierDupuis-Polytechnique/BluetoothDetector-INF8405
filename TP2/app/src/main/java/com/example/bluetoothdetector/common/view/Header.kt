package com.example.bluetoothdetector.common.view

import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.view.permissions.PermissionHeader
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel

@Composable
fun HeaderView(
    themeSelectorViewModel: ThemeSelectorViewModel,
    permissionsViewModel: PermissionsViewModel
) {
    CenteredHorizontalContainer {
        PermissionHeader(permissionsViewModel)
        ThemeSelector(themeSelectorViewModel)
    }
}