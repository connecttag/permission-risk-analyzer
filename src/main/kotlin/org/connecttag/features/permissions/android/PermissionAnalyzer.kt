package org.connecttag.features.permissions.android

/**
 * Produces permission reports for one or more installed packages.
 */
public interface PermissionAnalyzer {
    /**
     * Analyzes one package and returns a normalized permission report.
     */
    public suspend fun analyze(packageName: String): PermissionReport

    /**
     * Analyzes multiple packages in caller-defined order.
     */
    public suspend fun analyze(packageNames: Collection<String>): List<PermissionReport>
}


