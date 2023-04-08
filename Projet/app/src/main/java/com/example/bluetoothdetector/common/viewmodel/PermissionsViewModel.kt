package com.example.bluetoothdetector.common.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus

@OptIn(ExperimentalPermissionsApi::class)
class PermissionsViewModel : ViewModel() {

    // Stores the current permission modal display state
    val isPermissionModalShown = mutableStateOf(false)

    // Checks if all permissions are granted
    fun areAllRequiredPermissionsGranted(
        permissionsState: MultiplePermissionsState
    ): Boolean {
        return permissionsState.permissions.all {
            it.status == PermissionStatus.Granted
        }
    }

    // Shows the permission modal
    fun showPermissions() {
        isPermissionModalShown.value = true
    }

    // Hides the permission modal
    fun closePermissions() {
        isPermissionModalShown.value = false
    }

    // Launches Android internal permissions modal
    fun launchPermissionRequest(permissionsState: MultiplePermissionsState) {
        permissionsState.launchMultiplePermissionRequest()
    }

    fun canRequestPermission(permissionsState: MultiplePermissionsState): Boolean {
        return !areAllRequiredPermissionsGranted(permissionsState)
    }
}