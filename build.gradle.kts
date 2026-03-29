plugins {
    base
}

group = "com.monumentalrecipes"
version = providers.gradleProperty("mod_version").getOrElse("0.0.0-dev")

tasks.register("printStonecutterTargets") {
    group = "stonecutter"
    description = "Lists configured Stonecutter target directories."

    doLast {
        val versionsDir = layout.projectDirectory.dir("versions").asFile
        val targets = versionsDir.listFiles()
            ?.filter { it.isDirectory }
            ?.map { it.name }
            ?.sorted()
            .orEmpty()

        if (targets.isEmpty()) {
            println("No Stonecutter targets found under ./versions")
        } else {
            println("Configured Stonecutter targets:")
            targets.forEach { println(" - $it") }
        }
    }
}
