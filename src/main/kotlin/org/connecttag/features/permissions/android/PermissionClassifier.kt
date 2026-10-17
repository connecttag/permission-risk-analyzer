package org.connecttag.features.permissions.android

/**
 * Allows callers to override platform-specific permission classification rules.
 */
public interface PermissionClassifier {
    public fun classify(permissionName: String): PermissionProtection
}


