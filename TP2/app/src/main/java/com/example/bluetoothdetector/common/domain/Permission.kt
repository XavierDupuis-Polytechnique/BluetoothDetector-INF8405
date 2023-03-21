package com.example.bluetoothdetector.common.domain

// TODO : REMOVE
const val DefaultPermissionRationale = "DefaultPermissionRationale"

// TODO : REMOVE
data class PermissionRationale(
    val permission: String,
    val rationale: String = DefaultPermissionRationale
)
// TODO : REMOVE
typealias PermissionsRationales = List<PermissionRationale>

typealias Permission = String
typealias Permissions = List<Permission>