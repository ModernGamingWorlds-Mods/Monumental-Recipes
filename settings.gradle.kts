pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.neoforged.net/releases")
        maven("https://maven.kikugie.dev/releases")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9"
}

stonecutter {
    create(rootProject) {
        version("forge-1.18.2", "1.18.2")
        version("forge-1.19.2", "1.19.2")
        version("forge-1.20.1", "1.20.1")

        version("neoforge-1.20.1", "1.20.1")
        version("neoforge-1.21.1", "1.21.1")
        version("neoforge-26.1", "26.1")

        mapBuilds { _, data ->
            val loader = data.project.substringBefore('-')
            "$loader.gradle.kts"
        }

        vcsVersion = "neoforge-1.21.1"
    }
}
