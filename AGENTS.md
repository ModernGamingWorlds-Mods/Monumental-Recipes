# Repository Guidelines

## Project Structure & Module Organization

A pure datapack mod providing centralized recipes for the **Monumental Experience** modpack series, built with **Stonecutter 0.9** and **Gradle 9.4.1** for cross-version Minecraft builds.

- `src/main/java/com/monumentalrecipes/` — unified Java source using Stonecutter comment directives (`//? if mc:`) for version-conditional code
- `src/main/resources/data/<mod_name>/` — recipe JSON files organized by target mod (e.g., `ad_astra/`, `mekanism/`, `thermal/`)
- `versions/<loader>-<mc_version>/` — per-target `gradle.properties` and version-specific resources (`mods.toml`, `pack.mcmeta`)

Two loader-level build scripts at root are selected via Stonecutter `mapBuilds`:
- `forge.gradle.kts` — uses `net.neoforged.moddev.legacyforge` for all Forge targets
- `neoforge.gradle.kts` — uses `net.neoforged.moddev` for modern NeoForge (>=1.21), `net.neoforged.moddev.legacyforge` for NeoForge 1.20.1 (via `use_legacy_moddev` property)

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

# List configured Stonecutter targets
./gradlew printStonecutterTargets
```

## Supported Targets

| Loader   | Versions                |
|----------|-------------------------|
| Forge    | 1.18.2, 1.19.2, 1.20.1 |
| NeoForge | 1.20.1, 1.21.1, 26.1   |

Each target has its own `versions/<target>/gradle.properties` defining `minecraft_version`, `java_version`, `loader`, and loader-specific version properties.

## Adding Recipes

Recipe JSONs go in `src/main/resources/data/<mod_name>/recipes/`. These are standard Minecraft datapack recipes shared across all versions. Recipes that override vanilla go in `src/main/resources/vanilla_overrides/`. Version-specific resources belong in `versions/<target>/src/main/resources/`.

## Commit & Pull Request Guidelines

Commit messages use imperative mood, describing the change directly (e.g., "Fix mod version not propagating to subprojects"). AI-assisted PR branches follow the pattern `claude/<description>` or `codex/<description>`.
