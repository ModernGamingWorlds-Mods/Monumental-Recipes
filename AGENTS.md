# Repository Guidelines

## Project Structure & Module Organization

This is a multi-version Minecraft mod using **Stonecutter 0.9** for cross-version builds. The key architectural pattern:

- `src/forge/java/` — shared Java source for all Forge versions
- `src/neoforge/java/` — shared Java source for all NeoForge versions
- `src/main/resources/` — shared resources (datapack recipes live in `data/monumental_recipes/recipes/`)
- `versions/<loader>-<mc_version>/` — per-target build configs, each containing its own `build.gradle.kts`, `gradle.properties`, and version-specific resources (e.g., `mods.toml`, `pack.mcmeta`)

Source sets in each version's `build.gradle.kts` point back to the shared `src/` directories at the root. Forge targets use `net.minecraftforge.gradle`, NeoForge targets use `net.neoforged.moddev`.

The active Stonecutter target is set in `stonecutter.gradle.kts` (currently `neoforge-1.21.1`). All targets are registered in `settings.gradle.kts`.

## Build, Test, and Development Commands

```bash
# List all Stonecutter targets
./gradlew printStonecutterTargets

# Build a specific version (run from repo root)
./gradlew :forge-1.20.1:build
./gradlew :neoforge-1.21.1:build

# Run client/server for a specific version
./gradlew :neoforge-1.21.1:runClient
./gradlew :forge-1.20.1:runServer
```

Gradle is configured with parallel builds, configuration cache, and 2G max heap (`gradle.properties`). The project uses Gradle 9.0 via the included wrapper.

## Supported Targets

| Loader   | Versions                   |
|----------|----------------------------|
| Forge    | 1.16.5, 1.18.2, 1.19.2, 1.20.1 |
| NeoForge | 1.20.1, 1.21.1, 26.1      |

Each target has its own `gradle.properties` defining `loader`, `minecraft_version`, `java_version`, and the loader-specific version.

## Adding Recipes

Recipes go in `src/main/resources/data/monumental_recipes/recipes/`. These are standard Minecraft datapack JSON recipes shared across all versions. Version-specific resources belong in the corresponding `versions/<target>/src/main/resources/` directory.

## Commit & Pull Request Guidelines

Commit messages use imperative mood, describing the change directly (e.g., "Set up datapack structure for Forge and NeoForge across all versions", "Fix Stonecutter 0.9 setup and add Gradle 9.0 wrapper"). Branch names follow the pattern `<author>/<description>`.
