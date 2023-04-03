package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.common.domain.Modules
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.ui.theme.accepted
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionStatus(
    permissionsState: MultiplePermissionsState,
) {
    Column {
        Modules.forEach { module ->
            Subtitle(module.moduleType.name)
            Text(stringResource(module.description))
            module.permissions.forEach { permission ->
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
}
