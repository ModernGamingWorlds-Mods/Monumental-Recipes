plugins {
    java
    id("net.neoforged.gradle.userdev") version "7.0.+"
}

val minecraftVersion: String by project
val neoforgeVersion: String by project
val javaVersion: String by project

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
}

runs {
    configureEach {
        systemProperty("forge.logging.markers", "REGISTRIES")
        systemProperty("forge.logging.console.level", "debug")
        modSource(project.sourceSets["main"])
    }
    create("client") { workingDirectory(project.file("run/client")) }
    create("server") { workingDirectory(project.file("run/server")) }
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

dependencies {
    implementation("net.neoforged:neoforge:$neoforgeVersion")
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
