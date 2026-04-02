plugins {
    java
    id("net.neoforged.moddev") version "2.0.131"
}

val minecraft_version: String by project
val neoforge_version: String by project
val java_version: String by project

group = "com.monumentalrecipes"
version = providers.gradleProperty("mod_version").getOrElse("0.0.0-dev")
base.archivesName.set("monumental_recipes-neoforge-$minecraft_version")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(java_version.toInt()))
}

neoForge {
    version = neoforge_version

    runs {
        create("client") {
            client()
            gameDirectory.set(project.file("run/client"))
        }
        create("server") {
            server()
            gameDirectory.set(project.file("run/server"))
        }
    }

    mods {
        create("monumental_recipes") {
            sourceSet(sourceSets["main"])
        }
    }
}

sourceSets {
    main {
        java.setSrcDirs(listOf(rootProject.projectDir.resolve("src/neoforge/java")))
        resources.setSrcDirs(listOf(
            rootProject.projectDir.resolve("src/main/resources"),
            project.file("src/main/resources")
        ))
    }
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
