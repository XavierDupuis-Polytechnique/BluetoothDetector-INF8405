package com.example.bluetoothdetector.common.domain

import android.Manifest
import com.example.bluetoothdetector.R

// Module types
enum class ModuleType {
    MAPS,
    DETECTOR,
}

// Module class
data class Module(
    val moduleType: ModuleType,
    val description: Int,
    val permissions: Permissions = listOf()
)

// Maps module definition with related permissions
val MapsModule = Module(
    ModuleType.MAPS,
    R.string.maps_module_description,
    listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
)

val DetectorModule = Module(
    ModuleType.DETECTOR,
    R.string.detector_module_description,
    // Handle the bluetooth permission changes of API 31
    if (android.os.Build.VERSION.SDK_INT < 31) {
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
        )
    } else {
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH_CONNECT, // API 31+
            Manifest.permission.BLUETOOTH_SCAN, // API 31+
        )
    }
)

// List of current modules
val Modules = listOf(
    MapsModule,
    DetectorModule
)

// Retrieves all modules permissions
fun List<Module>.getAllPermissions(): List<String> {
    return map { it.permissions }.flatten()
}