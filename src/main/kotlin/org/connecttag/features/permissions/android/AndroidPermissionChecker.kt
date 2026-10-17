package org.connecttag.features.permissions.android

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Suppress("unused")
public class AndroidPermissionChecker(
    private val context: Context,
) : PermissionChecker {
    public override fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    public fun check(permission: String, activity: Activity? = null): RuntimePermissionState {
        return RuntimePermissionState(
            permission = permission,
            granted = hasPermission(permission),
            shouldShowRationale = activity?.let { ActivityCompat.shouldShowRequestPermissionRationale(it, permission) },
        )
    }

    public fun check(requirement: PermissionRequirement, activity: Activity? = null): RuntimePermissionState =
        check(requirement.permission, activity)

    public fun checkAll(requirements: Collection<PermissionRequirement>, activity: Activity? = null): List<RuntimePermissionState> =
        requirements.map { check(it, activity) }
}
