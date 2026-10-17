package org.connecttag.features.permissions.android

/**
 * Simple heuristic risk evaluator intended as a default starting point.
 *
 * Projects with stricter compliance or store-review requirements should replace
 * this implementation with domain-specific rules.
 */
public class DefaultPermissionRiskEvaluator : PermissionRiskEvaluator {

    companion object {
        const val HIGH_RISK_SCORE_THRESHOLD: Int = 50
        const val HIGH_RISK_PERMISSION_COUNT: Int = 4

        const val MEDIUM_RISK_SCORE_THRESHOLD: Int = 30
        const val MEDIUM_RISK_PERMISSION_COUNT: Int = 2
    }

    override fun evaluate(
        packageName: String,
        permissions: List<PermissionDescriptor>,
    ): PermissionRiskLevel {
        val grantedDangerousCount = permissions.count {
            it.protection == PermissionProtection.DANGEROUS && it.grantState == PermissionGrantState.GRANTED
        }
        val grantedSpecialCount = permissions.count {
            it.protection == PermissionProtection.SPECIAL && it.grantState == PermissionGrantState.GRANTED
        }

        return when {
            grantedSpecialCount >= 2 || grantedDangerousCount >= 6 -> PermissionRiskLevel.CRITICAL
            grantedSpecialCount >= 1 || grantedDangerousCount >= 4 -> PermissionRiskLevel.HIGH
            grantedDangerousCount >= 2 -> PermissionRiskLevel.MEDIUM
            else -> PermissionRiskLevel.LOW
        }
    }

    /**
     * Enhanced evaluation taking into account whether the app is a system app and its target SDK.
     */
    fun evaluate(
        packageName: String,
        permissions: List<PermissionDescriptor>,
        isSystem: Boolean,
        targetSdk: Int? = null,
    ): PermissionRiskLevel {
        if (isSystem) return PermissionRiskLevel.LOW
        val dangerousCount = permissions.count {
            it.protection == PermissionProtection.DANGEROUS && it.grantState == PermissionGrantState.GRANTED
        }
        val score = calculateRiskScore(isSystem, targetSdk, permissions, dangerousCount)
        return getRiskLevelFromScore(score, dangerousCount)
    }

    /**
     * Calculates an integer risk score (0 to 100) based on permissions, targetSdk, and system flags.
     */
    fun calculateRiskScore(
        isSystem: Boolean,
        targetSdk: Int?,
        permissions: List<PermissionDescriptor>,
        dangerousPermissionCount: Int = permissions.count {
            it.protection == PermissionProtection.DANGEROUS && it.grantState == PermissionGrantState.GRANTED
        }
    ): Int {
        return calculateRiskScore(
            isSystem = isSystem,
            targetSdk = targetSdk,
            permissionNames = permissions.map { it.name },
            dangerousPermissionCount = dangerousPermissionCount
        )
    }

    /**
     * Calculates an integer risk score (0 to 100) based on permission name strings, targetSdk, and system flags.
     */
    fun calculateRiskScore(
        isSystem: Boolean,
        targetSdk: Int?,
        permissionNames: Collection<String>,
        dangerousPermissionCount: Int
    ): Int {
        if (isSystem) return 5
        var score = 10
        val sdk = targetSdk ?: 35
        if (sdk < 31) score += 20
        if (sdk < 28) score += 25
        score += (dangerousPermissionCount * 12).coerceAtMost(50)
        if (permissionNames.any { it.contains("SYSTEM_ALERT_WINDOW", ignoreCase = true) }) score += 15
        if (permissionNames.any { it.contains("REQUEST_INSTALL_PACKAGES", ignoreCase = true) }) score += 15
        return score.coerceIn(0, 100)
    }

    /**
     * Maps risk score and dangerous count to a PermissionRiskLevel.
     */
    fun getRiskLevelFromScore(score: Int, dangerousCount: Int = 0): PermissionRiskLevel {
        return when {
            score >= 75 || dangerousCount >= 6 -> PermissionRiskLevel.CRITICAL
            score >= HIGH_RISK_SCORE_THRESHOLD || dangerousCount >= HIGH_RISK_PERMISSION_COUNT -> PermissionRiskLevel.HIGH
            score >= MEDIUM_RISK_SCORE_THRESHOLD || dangerousCount >= MEDIUM_RISK_PERMISSION_COUNT -> PermissionRiskLevel.MEDIUM
            else -> PermissionRiskLevel.LOW
        }
    }

    /**
     * Calculates an overall privacy health score for a fleet of applications (10 to 100).
     */
    fun calculatePrivacyHealthScore(
        totalApps: Int,
        sensitiveAppsCount: Int = 0,
        oldSdkCount: Int = 0
    ): Int {
        if (totalApps == 0) return 100
        var health = 100
        health -= (sensitiveAppsCount * 2).coerceAtMost(30)
        health -= (oldSdkCount * 2).coerceAtMost(20)
        return health.coerceIn(10, 100)
    }
}
