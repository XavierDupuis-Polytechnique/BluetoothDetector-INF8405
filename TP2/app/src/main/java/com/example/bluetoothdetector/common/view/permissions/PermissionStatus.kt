package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.domain.Modules
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionStatus(
    permissionsState: MultiplePermissionsState,
) {
    // val anyShouldShowRationale = permissionsState.permissions.any {
    //     it.status.shouldShowRationale
    // }
    Column {
        Modules.forEach { module ->
            Text(module.moduleType.name)
            Text(module.description)
            module.permissions.forEach { permission ->
                val isRevoked =
                    permissionsState.revokedPermissions.any { permissionState ->
                        permissionState.permission == permission
                    }
                Row {
                    if (isRevoked) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "",
                            tint = MaterialTheme.colors.error
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = ""
                        )
                    }
                    Text(permission)
                }
            }
        }
    }
}
