package org.connecttag.features.permissions.android

/**
 * Normalized representation of one requested permission.
 */
public data class PermissionDescriptor(
    val name: String,
    val protection: PermissionProtection,
    val grantState: PermissionGrantState,
    val isRuntime: Boolean,
    val isPrivileged: Boolean,
)


