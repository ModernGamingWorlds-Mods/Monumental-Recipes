<div align="center">

<img src="https://www.bisecthosting.com/images/CF/Monumental_Recipes/BH_Monumental_Recipes_Header.webp" alt="Monumental Recipes" />

<br><br>

<a href="https://bisecthosting.com/ModernGamingWorld">
  <img src="https://www.bisecthosting.com/images/CF/Monumental_Recipes/BH_Monumental_Recipes_Promo.webp" alt="BisectHosting" />
</a>

<br><br>

Monumental Recipes is a core mod that enhances the crafting experience by centralizing all custom recipes used across my modpacks.

It provides a structured and scalable system for managing unique recipes, ensuring they integrate seamlessly with progression and overall gameplay design.

---

## 🚀 Features

- Centralized repository for custom recipes  
- Designed specifically for my custom modpacks  
- Supports progression-based crafting systems  
- Simplifies recipe management and future expansion  

---

## ⚙️ Purpose

Monumental Recipes serves as the backbone for custom crafting within the Monumental Experience series.

Each recipe is carefully designed to align with progression, balance, and gameplay flow—making crafting more meaningful and consistent.

---

## 🛠️ Development Setup (Forge + NeoForge multi-version)

This project is set up to support the following target combinations with the **Stonecutter Dev** IntelliJ plugin.

### Supported targets

#### Forge

- 1.16.5
- 1.18.2
- 1.19.2
- 1.20.1

#### NeoForge

- 1.20.1
- 1.21.1
- 26.1

### 1) Install tools

1. Install **IntelliJ IDEA**.
2. Install the **Stonecutter Dev** plugin from JetBrains Marketplace:  
   <https://plugins.jetbrains.com/plugin/25044-stonecutter-dev>
3. Install a matching JDK for your target MC versions (JDK 17 for older targets, JDK 21 for newer 1.21+ targets).

### 2) Recommended project layout

```text
Monumental-Recipes/
├─ build.gradle.kts
├─ settings.gradle.kts
├─ gradle.properties
├─ versions/
│  ├─ forge-1.18.2/
│  ├─ forge-1.19.2/
│  ├─ forge-1.20.1/
│  ├─ neoforge-1.20.1/
│  ├─ neoforge-1.21.1/
│  └─ neoforge-26.1/
└─ src/
   └─ main/... (shared sources)
```

- Put common logic/resources in shared `src/main`.
- Put per-version overrides inside each `versions/<target>/` folder.

### 3) Stonecutter target matrix

| Target key       | Loader   | Version |
|------------------|----------|---------|
| `forge1182`      | Forge    | 1.18.2  |
| `forge1192`      | Forge    | 1.19.2  |
| `forge1201`      | Forge    | 1.20.1  |
| `neoforge1201`   | NeoForge | 1.20.1  |
| `neoforge1211`   | NeoForge | 1.21.1  |
| `neoforge261`    | NeoForge | 26.1    |

Keep this table synchronized with your Stonecutter targets so switching environments is predictable.

### 4) IntelliJ + Stonecutter workflow

1. Open the repository in IntelliJ.
2. Open the Stonecutter tool window.
3. Register/import each target environment from your `versions/` directories.
4. Mark shared/common source roots once.
5. Generate or refresh run configurations per target (`Client`, `Server`, `Data`).
6. Switch active target in Stonecutter before coding/testing a loader-specific change.

### 5) Practical branching strategy

- Keep **shared recipe/data logic** in common sources.
- Gate loader-specific code behind clearly isolated source sets/targets.
- Validate each target independently before release.
- Publish artifacts with explicit names per target version.

### 6) Minimum release checklist

For every supported target in the matrix:

- Gradle sync succeeds
- Compile/build succeeds
- Game launch works (Client at minimum)
- Data generation/recipes load without errors

---

## 📥 Installation

1. Install Minecraft Forge **or** NeoForge (compatible version)  
2. Download the latest release of Monumental Recipes  
3. Place the `.jar` file into your `mods` folder  
4. Launch the game  

---

## 📌 Notes

- Designed primarily for use within my custom modpacks  
- Fully compatible with other modpacks  
- Recipes only load if the required mods or items are present, ensuring stability and flexibility  

---

## 🐞 Support

If you encounter any issues or bugs, please open an issue on this repository.

---

## 📄 License

This project is licensed under the MIT License.  
See the `LICENSE` file for more details.

</div>
