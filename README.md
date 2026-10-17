# Permission Risk Analyzer

[![](https://jitpack.io/v/connecttag/permission-risk-analyzer.svg)](https://jitpack.io/#connecttag/permission-risk-analyzer)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A powerful Android security library for scanning, analyzing, and scoring permission risks of installed applications. Categorizes permissions into protection levels (Normal, Dangerous, Special, Signature) and computes risk evaluations.

## Features

- 🔍 **Deep Permission Inspection**: Scans installed applications and extracts all declared, requested, granted, and denied permissions.
- 🏷️ **Permission Classification**: Classifies permissions into protection categories: `NORMAL`, `DANGEROUS`, `SIGNATURE`, and `SPECIAL`.
- ⚠️ **Heuristic Risk Evaluator**: `DefaultPermissionRiskEvaluator` calculates risk scores (`LOW`, `MEDIUM`, `HIGH`, `CRITICAL`) based on dangerous and special permission combinations.
- 📊 **Permission Reports**: `PermissionReport` summarizes granted count, denied count, risk level, and full descriptor lists.
- ⚡ **Asynchronous Coroutines**: Runs all analysis off the main thread via Kotlin Coroutines.

## Installation

Add JitPack repository to your root `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://jitpack.io") }
    }
}
```

Add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.connecttag:permission-risk-analyzer:1.0.0")
}
```

## Quick Start

```kotlin
import org.connecttag.features.permissions.android.AndroidPermissionAnalyzer
import org.connecttag.features.permissions.android.PermissionRiskLevel

val analyzer = AndroidPermissionAnalyzer(context)

lifecycleScope.launch {
    val report = analyzer.analyze("com.example.targetapp")
    
    println("Target: ${report.packageName}")
    println("Risk Level: ${report.riskLevel}") // LOW, MEDIUM, HIGH, CRITICAL
    println("Granted: ${report.grantedCount}, Denied: ${report.deniedCount}")
    
    report.permissions.forEach { perm ->
        println("- ${perm.name} [Protection: ${perm.protection}, Status: ${perm.grantState}]")
    }
}
```

## License

```
Copyright 2026 Connect Tag

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
```
