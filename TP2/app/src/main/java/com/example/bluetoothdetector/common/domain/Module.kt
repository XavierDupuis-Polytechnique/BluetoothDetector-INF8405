package com.example.bluetoothdetector.common.domain

import android.Manifest

enum class ModuleType {
    MAPS,
    DETECTOR,
}

data class Module(
    val moduleType: ModuleType,
    val description: String,
    val permissions: Permissions = listOf()
)

val Modules = listOf(
    Module(
        ModuleType.MAPS,
        "ModuleType.MAPS",
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ),
    Module(
        ModuleType.DETECTOR,
        "ModuleType.DETECTOR",
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            // https://developer.android.com/guide/topics/connectivity/bluetooth/permissions
            // Manifest.permission.ACCESS_BACKGROUND_LOCATION
            // Manifest.permission.BLUETOOTH_CONNECT,
            // Manifest.permission.BLUETOOTH_SCAN,
        )
    )
)

fun List<Module>.getAllPermissions(): List<String> {
    return map { it.permissions }.flatten()
}