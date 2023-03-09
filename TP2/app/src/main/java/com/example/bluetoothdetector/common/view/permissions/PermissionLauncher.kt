package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
            title = {
                Text(
                    text = "Permission Request",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                PermissionStatus(
                    permissionsState,
//                    permissionsRationales
                )
            },
            confirmButton = {
                Button(
                    onClick = confirm,
                    enabled = viewModel.canRequestPermission(permissionsState)
                ) {
                    Text("Give Permission")
                }
            },
            dismissButton = {
                Button(
                    onClick = dismiss
                ) {
                    Text("Close")
                }
            }
        )
    }
}