plugins {
    id("dev.kikugie.stonecutter")
    id("net.neoforged.moddev") version "2.0.141" apply false
    id("net.neoforged.moddev.legacyforge") version "2.0.141" apply false
}

stonecutter active "neoforge-1.21.1" /* [SC] DO NOT EDIT */

tasks.register("chiseledBuild") {
    group = "project"
    dependsOn(stonecutter.versions.map { ":${it.project}:build" })
}
