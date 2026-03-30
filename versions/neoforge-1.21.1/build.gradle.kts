plugins {
    java
    id("net.neoforged.moddev") version "2.0.+"
}

val minecraftVersion: String by project
val neoforgeVersion: String by project
val javaVersion: String by project

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
}

neoForge {
    version = neoforgeVersion

    runs {
        create("client") {
            client()
            workingDirectory(project.file("run/client"))
        }
        create("server") {
            server()
            workingDirectory(project.file("run/server"))
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
