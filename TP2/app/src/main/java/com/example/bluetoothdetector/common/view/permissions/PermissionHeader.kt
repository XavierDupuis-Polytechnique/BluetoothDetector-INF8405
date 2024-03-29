package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.domain.Module
import com.example.bluetoothdetector.common.domain.Modules
import com.example.bluetoothdetector.common.domain.getAllPermissions
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.ui.theme.PERMISSIONS_GRANTED
import com.example.bluetoothdetector.ui.theme.PERMISSIONS_REQUIRED
import com.example.bluetoothdetector.ui.theme.accepted
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


// Header button to access permissions
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHeader(
    viewModel: PermissionsViewModel,
    modules: List<Module> = Modules
) {
    val permissions: List<String> = modules.getAllPermissions()
    val permissionsState = rememberMultiplePermissionsState(permissions)
    val allPermissionsGranted =
        viewModel.areAllRequiredPermissionsGranted(permissionsState)

    // Error color if at least one permission is missing
    val backgroundColor =
        if (allPermissionsGranted) MaterialTheme.colors.accepted else MaterialTheme.colors.error
    val contentColor = MaterialTheme.colors.onError

    Button(
        onClick = { viewModel.showPermissions() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor.copy(alpha = 0.8f),
            contentColor = contentColor
        )
    ) {
        Text(if (allPermissionsGranted) PERMISSIONS_GRANTED else PERMISSIONS_REQUIRED)
    }
    PermissionLauncher(
        viewModel,
        permissionsState,
    ) {
        viewModel.launchPermissionRequest(permissionsState)
    }
}