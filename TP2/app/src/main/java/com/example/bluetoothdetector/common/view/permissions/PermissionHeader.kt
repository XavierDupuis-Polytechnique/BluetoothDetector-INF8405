package com.example.bluetoothdetector.common.view.permissions

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.domain.Module
import com.example.bluetoothdetector.common.domain.Modules
import com.example.bluetoothdetector.common.domain.getAllPermissions
import com.example.bluetoothdetector.common.view.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHeader(
    viewModel: PermissionsViewModel,
    modules: List<Module> = Modules
) {
    val permissions: List<String> = modules.getAllPermissions()
    val permissionsState = rememberMultiplePermissionsState(permissions)
    CenteredHorizontalContainer {
        Text("PERM HEADER ")
        if (!viewModel.areAllRequiredPermissionsGranted(permissionsState)) {
            Button(onClick = { viewModel.showPermissions() }) {
                Text("launchPermissionRequest")
            }
        } else {
            Button(onClick = { viewModel.showPermissions() }) {
                Text("all perms granted")
            }
        }
    }
    PermissionLauncher(
        viewModel,
        permissionsState,
    ) {
        viewModel.launchPermissionRequest(permissionsState)
    }
}