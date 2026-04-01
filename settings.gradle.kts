pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        mavenCentral()
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases")
    }
}

rootProject.name = "Monumental-Recipes"

plugins {
    id("dev.kikugie.stonecutter") version "0.9"
}

stonecutter {
    create(rootProject) {
        version("forge-1.16.5", "1.16.5")
        version("forge-1.18.2", "1.18.2")
        version("forge-1.19.2", "1.19.2")
        version("forge-1.20.1", "1.20.1")

        version("neoforge-1.20.1", "1.20.1")
        version("neoforge-1.21.1", "1.21.1")
        version("neoforge-26.1", "26.1")
    }
}
