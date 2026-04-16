import gg.meza.stonecraft.mod
import java.util.*

plugins {
    id("gg.meza.stonecraft")
    id("me.modmuss50.mod-publish-plugin")
}

// Bump the patch version in gradle.properties (e.g. 1.0.0 -> 1.0.1)
// Run this task before publishing a new release.
tasks.register("bumpVersion") {
    group = "versioned"
    description = "Bumps the patch version in gradle.properties (e.g. 1.0.0 -> 1.0.1)"
    doLast {
        val propsFile = rootProject.file("gradle.properties")
        val props = Properties()
        props.load(propsFile.inputStream())
        val current = props.getProperty("mod.version")
        val parts = current.split(".").toMutableList()
        parts[2] = (parts[2].toInt() + 1).toString()
        val newVersion = parts.joinToString(".")
        props.setProperty("mod.version", newVersion)
        propsFile.outputStream().use { props.store(it, null) }
        println("Version bumped: $current -> $newVersion")
    }
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

publishMods {
    val modrinthToken = System.getenv("MODRINTH_TOKEN")
        ?: localProperties.getProperty("publish.modrinthToken", "")
    val curseforgeToken = System.getenv("CURSEFORGE_TOKEN")
        ?: localProperties.getProperty("publish.curseforgeToken", "")

    dryRun = modrinthToken.isEmpty() || curseforgeToken.isEmpty()
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = BETA

    modrinth {
        projectId = "iF5z0VHr"
        accessToken = modrinthToken
    }

    curseforge {
        projectId = "863339"
        accessToken = curseforgeToken
        clientRequired = true
        serverRequired = true
    }
}

val minecraft = stonecutter.current.version

tasks.processResources {
    // NeoForge 1.21+ uses "recipe/" (singular), older versions use "recipes/" (plural)
    if (stonecutter.eval(minecraft, ">=1.21")) {
        exclude("**/data/*/recipes/**")
    } else {
        exclude("**/data/*/recipe/**")
    }

    // Exclude Create worldgen from 1.19.2 and below
    if (stonecutter.eval(minecraft, "<1.20")) {
        exclude("**/data/create/worldgen/**")
    }
}
