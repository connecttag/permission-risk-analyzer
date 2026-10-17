plugins {
    alias(libs.plugins.android.library)
    `maven-publish`
}

group = "org.connecttag.libs.kotlin"
version = "1.0.0"

android {
    namespace = "org.connecttag.features.permissions.android"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.kotlinx.coroutines.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "org.connecttag.libs.kotlin"
                artifactId = "permission-risk-analyzer"
                version = "1.0.0"

                pom {
                    name.set("ConnectTag Permission Risk Analyzer")
                    description.set("Android security library for inspecting, classifying, and evaluating permission risks and protection levels of installed packages")
                    url.set("https://github.com/connecttag/permission-risk-analyzer")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("connecttagye")
                            name.set("Connect Tag")
                            email.set("connecttagye@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/connecttag/permission-risk-analyzer.git")
                        developerConnection.set("scm:git:ssh://git@github.com:connecttag/permission-risk-analyzer.git")
                        url.set("https://github.com/connecttag/permission-risk-analyzer/tree/main")
                    }
                }
            }
        }
    }
}
