# Repository Guidelines

## Project Structure & Module Organization

This is a multi-version Minecraft mod using **Stonecutter 0.9** with **Gradle 9.4.1** for cross-version builds. The key architectural pattern:

- `src/forge/java/` — shared Java source for all Forge versions (also used by NeoForge 1.20.1 which shares forge package names)
- `src/neoforge/java/` — shared Java source for NeoForge 1.21.1+
- `src/main/resources/` — shared resources (datapack recipes live in `data/monumental_recipes/recipes/`)
- `versions/<loader>-<mc_version>/` — per-target `gradle.properties` and version-specific resources (e.g., `mods.toml`, `pack.mcmeta`)

Two loader-level build scripts at root are selected via Stonecutter `mapBuilds`:
- `forge.gradle.kts` — uses `net.neoforged.moddev.legacyforge` for all Forge targets
- `neoforge.gradle.kts` — uses `net.neoforged.moddev` for modern NeoForge, `net.neoforged.moddev.legacyforge` for NeoForge 1.20.1 (via `use_legacy_moddev` property)

Plugins are declared in `stonecutter.gradle.kts` with `apply false` and applied conditionally in loader scripts.

## Build, Test, and Development Commands

```bash
# Build all targets
./gradlew chiseledBuild

# Build a specific version
./gradlew :forge-1.20.1:build
./gradlew :neoforge-1.21.1:build

# Run client/server for a specific version
./gradlew :neoforge-1.21.1:runClient
./gradlew :forge-1.20.1:runServer
```

## Supported Targets

| Loader   | Versions              |
|----------|-----------------------|
| Forge    | 1.18.2, 1.19.2, 1.20.1 |
| NeoForge | 1.20.1, 1.21.1, 26.1 |

Each target has its own `gradle.properties` defining `minecraft_version`, `java_version`, `loader`, and the loader-specific version.

## Adding Recipes

Recipes go in `src/main/resources/data/monumental_recipes/recipes/`. These are standard Minecraft datapack JSON recipes shared across all versions. Version-specific resources belong in the corresponding `versions/<target>/src/main/resources/` directory.

## Commit & Pull Request Guidelines

Commit messages use imperative mood, describing the change directly. Branch names follow the pattern `<author>/<description>`.
