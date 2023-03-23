package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.typography.Title
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
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
            title = { Title(stringResource(R.string.permissions_status)) },
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
                    Text(stringResource(R.string.grant_permissions) )
                }
            },
            dismissButton = {
                Button(
                    onClick = dismiss
                ) {
                    Text(stringResource(R.string.close) )
                }
            }
        )
    }
}