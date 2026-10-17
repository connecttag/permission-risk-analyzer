package org.connecttag.features.permissions.android

/**
 * Converts normalized permission descriptors into a package-level risk decision.
 */
public interface PermissionRiskEvaluator {
    public fun evaluate(packageName: String, permissions: List<PermissionDescriptor>): PermissionRiskLevel
}


