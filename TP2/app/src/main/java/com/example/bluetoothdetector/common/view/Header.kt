package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.view.permissions.PermissionHeader
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel

@Composable
fun HeaderView(
    themeSelectorViewModel: ThemeSelectorViewModel,
    permissionsViewModel: PermissionsViewModel
) {
    CenteredHorizontalContainer(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        PermissionHeader(permissionsViewModel)
        Spacer(modifier = Modifier.weight(1f))
        ThemeSelector(themeSelectorViewModel)
    }
}