plugins {
    java
    id("net.neoforged.gradle.userdev") version "7.0.157"
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
        // NeoForge 1.20.1 still uses net.minecraftforge package names, so use the Forge sources
        java.setSrcDirs(listOf(rootProject.projectDir.resolve("src/forge/java")))
        resources.setSrcDirs(listOf(
            rootProject.projectDir.resolve("src/main/resources"),
            project.file("src/main/resources")
        ))
    }
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
}

dependencies {
    implementation("net.neoforged:forge:$neoforge_version")
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
