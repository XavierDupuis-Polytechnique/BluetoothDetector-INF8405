package com.example.bluetoothdetector.common.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus

@OptIn(ExperimentalPermissionsApi::class)
class PermissionsViewModel : ViewModel() {

    val isRationaleShown = mutableStateOf(false)

    fun areAllRequiredPermissionsGranted(
        permissionsState: MultiplePermissionsState
    ): Boolean {
        return permissionsState.permissions.all {
            it.status == PermissionStatus.Granted
        }
    }

    fun showPermissions() {
        isRationaleShown.value = true
    }

    fun closePermissions() {
        isRationaleShown.value = false
    }

    fun launchPermissionRequest(permissionsState: MultiplePermissionsState) {
        permissionsState.launchMultiplePermissionRequest()
    }

    fun canRequestPermission(permissionsState: MultiplePermissionsState): Boolean {
        return !areAllRequiredPermissionsGranted(permissionsState)
    }
}