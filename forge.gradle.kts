plugins {
    java
    id("net.minecraftforge.gradle") version "6.0.25"
}

val minecraft_version: String by project
val forge_version: String by project
val java_version: String by project

group = "com.monumentalrecipes"
version = providers.gradleProperty("mod_version").getOrElse("0.0.0-dev")
base.archivesName.set("monumental_recipes-forge-$minecraft_version")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(java_version.toInt()))
}

minecraft {
    mappings("official", minecraft_version)

    runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods {
                create("monumental_recipes") {
                    source(sourceSets["main"])
                }
            }
        }
        create("server") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods {
                create("monumental_recipes") {
                    source(sourceSets["main"])
                }
            }
        }
    }
}

sourceSets {
    main {
        java.setSrcDirs(listOf(rootProject.projectDir.resolve("src/forge/java")))
        resources.setSrcDirs(listOf(
            rootProject.projectDir.resolve("src/main/resources"),
            project.file("src/main/resources")
        ))
    }
}

dependencies {
    minecraft("net.minecraftforge:forge:$minecraft_version-$forge_version")
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
            "Specification-Title"        to "monumental_recipes",
            "Specification-Vendor"       to "ModernGamingWorlds",
            "Specification-Version"      to "1",
            "Implementation-Title"       to project.name,
            "Implementation-Version"     to project.version,
            "Implementation-Vendor"      to "ModernGamingWorlds"
        ))
    }
}
