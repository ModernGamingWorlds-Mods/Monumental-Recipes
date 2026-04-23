pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.kikugie.dev/snapshots")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases/")
    }
}
plugins {
    id("gg.meza.stonecraft") version "1.9.+"
    id("dev.kikugie.stonecutter") version "0.9.+"
    id("me.modmuss50.mod-publish-plugin") version "1.1.0" apply false
}

stonecutter {
    centralScript = "build.gradle.kts"
    kotlinController = true
    shared {
        fun mc(version: String, vararg loaders: String) {
            for (it in loaders) version("$version-$it", version)
        }

        mc("1.20.1", "forge")
        mc("1.19.2", "forge")
        mc("1.18.2", "forge")
        mc("1.21.1", "neoforge")

        vcsVersion = "1.20.1-forge"
    }
    create(rootProject)
}

rootProject.name = "Monumental Recipes"
