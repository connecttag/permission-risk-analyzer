package org.connecttag.features.permissions.android

public interface PermissionChecker {
    public fun hasPermission(permission: String): Boolean

    public fun hasPermissions(permissions: Collection<String>): Map<String, Boolean> =
        permissions.associateWith { hasPermission(it) }
}

public data class PermissionRequirement(
    val permission: String,
    val minSdk: Int = 1,
    val reason: String? = null,
    val feature: String? = null,
)

public data class RuntimePermissionState(
    val permission: String,
    val granted: Boolean,
    val shouldShowRationale: Boolean? = null,
)



