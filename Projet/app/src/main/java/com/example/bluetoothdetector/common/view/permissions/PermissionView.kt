package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.Module
import com.example.bluetoothdetector.common.domain.Modules
import com.example.bluetoothdetector.common.domain.getAllPermissions
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.ui.theme.accepted
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

// Internal permission modal content listing all permissions
// per module and their current individual state
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsView(
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


    CenteredVerticalContainer {
        Subtitle(
            subtitle = if (allPermissionsGranted)
                stringResource(R.string.permissions_granted)
            else
                stringResource(R.string.permissions_required),
            color = contentColor,
            modifier = Modifier.background(backgroundColor.copy(alpha = 0.8f))
        )
        Column {
            Modules.forEach { module ->
                Subtitle(module.moduleType.name)
                Text(stringResource(module.description))
                module.permissions.forEach { permission ->
                    // Find if permission is currently revoked
                    val isRevoked =
                        permissionsState.revokedPermissions.any { permissionState ->
                            permissionState.permission == permission
                        }
                    Row {
                        Icon(
                            imageVector = if (isRevoked) Icons.Default.Error else Icons.Default.CheckCircle,
                            tint = if (isRevoked) MaterialTheme.colors.error else MaterialTheme.colors.accepted,
                            modifier = Modifier.padding(horizontal = 6.dp),
                            contentDescription = "${!isRevoked}"
                        )
                        Text(permission)
                    }
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
        Button(
            onClick = { viewModel.launchPermissionRequest(permissionsState) },
            enabled = viewModel.canRequestPermission(permissionsState)
        ) {
            Text(stringResource(R.string.grant_permissions))
        }
    }
}
