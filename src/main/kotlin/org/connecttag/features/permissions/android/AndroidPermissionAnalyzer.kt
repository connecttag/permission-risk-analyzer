package org.connecttag.features.permissions.android

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import android.os.Build
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Android-backed implementation of [PermissionAnalyzer].
 */
public class AndroidPermissionAnalyzer(
    context: Context,
    private val packageManager: PackageManager = context.packageManager,
    private val classifier: PermissionClassifier = DefaultPermissionClassifier(),
    private val riskEvaluator: PermissionRiskEvaluator = DefaultPermissionRiskEvaluator(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : PermissionAnalyzer {

    override suspend fun analyze(packageName: String): PermissionReport = withContext(dispatcher) {
        val packageInfo = getPackageInfo(packageName)
        val permissions = packageInfo.requestedPermissions ?: emptyArray<String>()
        val flags = packageInfo.requestedPermissionsFlags ?: intArrayOf()

        val descriptors = permissions.mapIndexed { index, permissionName ->
            val permissionInfo = resolvePermissionInfo(permissionName)
            val protection = resolveProtection(permissionName, permissionInfo)
            val grantState = resolveGrantState(packageName, permissionName, flags.getOrNull(index))

            PermissionDescriptor(
                name = permissionName,
                protection = protection,
                grantState = grantState,
                isRuntime = protection == PermissionProtection.DANGEROUS,
                isPrivileged = permissionInfo?.let { it.protectionLevel and PermissionInfo.PROTECTION_FLAG_PRIVILEGED != 0 } == true,
            )
        }

        PermissionReport(
            packageName = packageName,
            permissions = descriptors,
            riskLevel = riskEvaluator.evaluate(packageName, descriptors),
            grantedCount = descriptors.count { it.grantState == PermissionGrantState.GRANTED },
            deniedCount = descriptors.count { it.grantState == PermissionGrantState.DENIED },
        )
    }

    override suspend fun analyze(packageNames: Collection<String>): List<PermissionReport> =
        packageNames.map { analyze(it) }

    @Suppress("DEPRECATION")
    private fun getPackageInfo(packageName: String): PackageInfo {
        val flags = PackageManager.GET_PERMISSIONS
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
        } else {
            packageManager.getPackageInfo(packageName, flags)
        }
    }

    @Suppress("DEPRECATION")
    private fun resolvePermissionInfo(permissionName: String): PermissionInfo? = runCatching {
        packageManager.getPermissionInfo(permissionName, 0)
    }.getOrNull()

    private fun resolveProtection(
        permissionName: String,
        permissionInfo: PermissionInfo?,
    ): PermissionProtection {
        if (classifier.classify(permissionName) == PermissionProtection.SPECIAL) {
            return PermissionProtection.SPECIAL
        }

        val protectionLevel = permissionInfo?.protectionLevel ?: return classifier.classify(permissionName)
        val baseProtection = protectionLevel and PermissionInfo.PROTECTION_MASK_BASE

        return when (baseProtection) {
            PermissionInfo.PROTECTION_NORMAL -> PermissionProtection.NORMAL
            PermissionInfo.PROTECTION_DANGEROUS -> PermissionProtection.DANGEROUS
            PermissionInfo.PROTECTION_SIGNATURE -> PermissionProtection.SIGNATURE
            PermissionInfo.PROTECTION_INTERNAL -> PermissionProtection.INTERNAL
            else -> classifier.classify(permissionName)
        }
    }

    private fun resolveGrantState(
        packageName: String,
        permissionName: String,
        requestedPermissionFlag: Int?,
    ): PermissionGrantState {
        if (requestedPermissionFlag == null) return PermissionGrantState.UNKNOWN
        if (requestedPermissionFlag and PackageInfo.REQUESTED_PERMISSION_GRANTED != 0) {
            return PermissionGrantState.GRANTED
        }
        return when (packageManager.checkPermission(permissionName, packageName)) {
            PackageManager.PERMISSION_GRANTED -> PermissionGrantState.GRANTED
            PackageManager.PERMISSION_DENIED -> PermissionGrantState.DENIED
            else -> PermissionGrantState.UNKNOWN
        }
    }
}

