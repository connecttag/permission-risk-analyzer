package org.connecttag.features.permissions.android

/**
 * Aggregated permission analysis for a package.
 */
public data class PermissionReport(
    val packageName: String,
    val permissions: List<PermissionDescriptor>,
    val riskLevel: PermissionRiskLevel,
    val grantedCount: Int,
    val deniedCount: Int,
)


