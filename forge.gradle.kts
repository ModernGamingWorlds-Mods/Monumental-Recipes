plugins {
    java
    id("net.neoforged.moddev.legacyforge")
}

val minecraft_version: String = property("minecraft_version") as String
val forge_version: String = property("forge_version") as String
val java_version: String = property("java_version") as String

group = "com.monumentalrecipes"
version = providers.gradleProperty("mod_version").getOrElse("0.0.0-dev")
base.archivesName.set("monumental_recipes-forge-$minecraft_version")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(java_version.toInt()))
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
}

legacyForge {
    version = forge_version
    runs {
        register("client") { client() }
        register("server") { server() }
    }
    mods {
        register("monumental_recipes") {
            sourceSet(sourceSets.main.get())
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

tasks.named("createMinecraftArtifacts") {
    dependsOn("stonecutterGenerate")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes(
            "Specification-Title"    to "monumental_recipes",
            "Specification-Vendor"   to "ModernGamingWorlds",
            "Specification-Version"  to "1",
            "Implementation-Title"   to "monumental_recipes",
            "Implementation-Version" to project.version,
            "Implementation-Vendor"  to "ModernGamingWorlds"
        )
    }
}
