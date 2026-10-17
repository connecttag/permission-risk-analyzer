package org.connecttag.features.permissions.android


/**
 * Simple heuristic risk evaluator intended as a default starting point.
 *
 * Projects with stricter compliance or store-review requirements should replace
 * this implementation with domain-specific rules.
 */
public class DefaultPermissionRiskEvaluator : PermissionRiskEvaluator {
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
}

