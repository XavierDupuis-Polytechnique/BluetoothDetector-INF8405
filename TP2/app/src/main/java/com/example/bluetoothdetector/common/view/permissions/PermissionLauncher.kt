package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.view.typography.Title
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.ui.theme.CLOSE
import com.example.bluetoothdetector.ui.theme.GRANT_PERMISSIONS
import com.example.bluetoothdetector.ui.theme.PERMISSIONS_STATUS
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionLauncher(
    viewModel: PermissionsViewModel,
    permissionsState: MultiplePermissionsState,
    launchPermissionsRequest: () -> Unit
) {
    val dismiss: () -> Unit = {
        viewModel.closePermissions()
    }
    val confirm: () -> Unit = {
        dismiss()
        launchPermissionsRequest()
    }
    if (viewModel.isRationaleShown.value) {
        AlertDialog(
            onDismissRequest = {},
            title = { Title(PERMISSIONS_STATUS) },
            text = {
                PermissionStatus(
                    permissionsState,
                )
            },
            confirmButton = {
                Button(
                    onClick = confirm,
                    enabled = viewModel.canRequestPermission(permissionsState)
                ) {
                    Text(GRANT_PERMISSIONS)
                }
            },
            dismissButton = {
                Button(
                    onClick = dismiss
                ) {
                    Text(CLOSE)
                }
            }
        )
    }
}