plugins {
    java
    id("net.minecraftforge.gradle") version "6.0.+"
}

val minecraftVersion: String by project
val forgeVersion: String by project
val javaVersion: String by project

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
}

minecraft {
    mappings("official", minecraftVersion)

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
    minecraft("net.minecraftforge:forge:$minecraftVersion-$forgeVersion")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Specification-Title"        to "monumental_recipes",
            "Specification-Vendor"       to "ModernGamingWorlds",
            "Specification-Version"      to "1",
            "Implementation-Title"       to project.name,
            "Implementation-Version"     to project.version,
            "Implementation-Vendor"      to "ModernGamingWorlds",
            "Implementation-Timestamp"   to java.time.LocalDateTime.now()
        )
    }
}
