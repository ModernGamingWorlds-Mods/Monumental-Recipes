val useLegacy = findProperty("use_legacy_moddev") == "true"

plugins {
    java
    id("net.neoforged.moddev") apply false
    id("net.neoforged.moddev.legacyforge") apply false
}

if (useLegacy) {
    apply(plugin = "net.neoforged.moddev.legacyforge")
} else {
    apply(plugin = "net.neoforged.moddev")
}

val minecraft_version: String = property("minecraft_version") as String
val neoforge_version: String = property("neoforge_version") as String
val java_version: String = property("java_version") as String
val isModern = stonecutter.eval(stonecutter.current.version, ">=1.21")

group = "com.monumentalrecipes"
version = providers.gradleProperty("mod_version").getOrElse("0.0.0-dev")
base.archivesName.set("monumental_recipes-neoforge-$minecraft_version")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(java_version.toInt()))
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
}

if (isModern) {
    extensions.configure<net.neoforged.moddevgradle.dsl.NeoForgeExtension>("neoForge") {
        version = neoforge_version
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
} else {
    extensions.configure<net.neoforged.moddevgradle.legacyforge.dsl.LegacyForgeExtension>("legacyForge") {
        enable {
            neoForgeVersion = neoforge_version
        }
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
}

sourceSets {
    main {
        java.setSrcDirs(listOf(
            if (isModern) rootProject.projectDir.resolve("src/neoforge/java")
            else rootProject.projectDir.resolve("src/forge/java")
        ))
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

tasks.processResources {
    if (isModern) {
        exclude("META-INF/mods.toml")
    } else {
        exclude("META-INF/neoforge.mods.toml")
    }
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
