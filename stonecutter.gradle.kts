plugins {
    id("dev.kikugie.stonecutter")
    id("net.neoforged.moddev") version "2.0.141" apply false
    id("net.neoforged.moddev.legacyforge") version "2.0.141" apply false
}

stonecutter active "neoforge-1.21.1" /* [SC] DO NOT EDIT */

stonecutter parameters {
    val loader = node.metadata.project.substringBefore('-')
    constants.match(loader, "forge", "neoforge")

    constants["standalone_ebs"] = node.metadata.version.let {
        stonecutter.eval(it, ">=1.21")
    }

    dependencies["mc"] = node.metadata.version
}

tasks.register("chiseledBuild") {
    group = "project"
    dependsOn(stonecutter.versions.map { ":${it.project}:build" })
}
