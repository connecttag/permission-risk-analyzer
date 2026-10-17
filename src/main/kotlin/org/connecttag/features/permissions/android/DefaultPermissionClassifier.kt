package org.connecttag.features.permissions.android

import android.Manifest

/**
 * Baseline classifier for Android special permissions that are not reliably
 * represented as normal runtime permissions in caller-facing APIs.
 */
public class DefaultPermissionClassifier : PermissionClassifier {
    override fun classify(permissionName: String): PermissionProtection {
        return when (permissionName) {
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.PACKAGE_USAGE_STATS,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.SCHEDULE_EXACT_ALARM,
            Manifest.permission.POST_NOTIFICATIONS -> PermissionProtection.SPECIAL
            else -> PermissionProtection.UNKNOWN
        }
    }
}

