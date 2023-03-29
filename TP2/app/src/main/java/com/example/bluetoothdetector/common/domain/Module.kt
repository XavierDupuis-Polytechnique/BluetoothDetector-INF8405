package com.example.bluetoothdetector.common.domain

import android.Manifest
import com.example.bluetoothdetector.ui.theme.DETECTOR_MODULE_DESCRIPTION
import com.example.bluetoothdetector.ui.theme.MAPS_MODULE_DESCRIPTION

enum class ModuleType {
    MAPS,
    DETECTOR,
}

data class Module(
    val moduleType: ModuleType,
    val description: String = moduleType.name,
    val permissions: Permissions = listOf()
)

val Modules = listOf(
    Module(
        ModuleType.MAPS,
        MAPS_MODULE_DESCRIPTION,
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ),
    Module(
        ModuleType.DETECTOR,
        DETECTOR_MODULE_DESCRIPTION,
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            // TODO : INVESTIGATE -> ADD / REMOVE
            // https://developer.android.com/guide/topics/connectivity/bluetooth/permissions
            // Manifest.permission.ACCESS_BACKGROUND_LOCATION
             Manifest.permission.BLUETOOTH_CONNECT, // API 31+
             Manifest.permission.BLUETOOTH_SCAN, // API 31+
        )
    )
)

fun List<Module>.getAllPermissions(): List<String> {
    return map { it.permissions }.flatten()
}