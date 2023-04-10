package com.example.bluetoothdetector.common.viewmodel

import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus

@OptIn(ExperimentalPermissionsApi::class)
class PermissionsViewModel : ViewModel() {
    // Checks if all permissions are granted
    fun areAllRequiredPermissionsGranted(
        permissionsState: MultiplePermissionsState
    ): Boolean {
        return permissionsState.permissions.all {
            it.status == PermissionStatus.Granted
        }
    }

    // Launches Android internal permissions modal
    fun launchPermissionRequest(permissionsState: MultiplePermissionsState) {
        permissionsState.launchMultiplePermissionRequest()
    }

    fun canRequestPermission(permissionsState: MultiplePermissionsState): Boolean {
        return !areAllRequiredPermissionsGranted(permissionsState)
    }
}