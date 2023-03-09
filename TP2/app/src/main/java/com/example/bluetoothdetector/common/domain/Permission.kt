package com.example.bluetoothdetector.common.domain

const val DefaultPermissionRationale = "DefaultPermissionRationale"

data class PermissionRationale(
    val permission: String,
    val rationale: String = DefaultPermissionRationale
)
typealias PermissionsRationales = List<PermissionRationale>

typealias Permission = String
typealias Permissions = List<Permission>