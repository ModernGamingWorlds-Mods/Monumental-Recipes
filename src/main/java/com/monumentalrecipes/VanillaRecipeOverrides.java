package com.monumentalrecipes;

//? if mc: <1.21 {
/*import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;*/
//?} else {
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
//? if standalone_ebs {
import net.neoforged.fml.common.EventBusSubscriber;
//?} else
/*import net.neoforged.fml.common.Mod;*/
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//?}

//? if mc: <1.21 {
/*@Mod.EventBusSubscriber(modid = MonumentalRecipes.MOD_ID)*/
//?} else if standalone_ebs {
@EventBusSubscriber(modid = MonumentalRecipes.MOD_ID)
//?} else
/*@Mod.EventBusSubscriber(modid = MonumentalRecipes.MOD_ID)*/
public class VanillaRecipeOverrides {

    private static final Pattern NS_PATTERN = Pattern.compile("\"([a-z_][a-z0-9_]*):[a-z0-9_/\\.]+\"");
    private static final Set<String> SKIP_NS = Set.of("minecraft", "forge", "neoforge", "c");

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        //? if mc: <1.21 {
        /*try {
            applyOverrides(event.getServer());
        } catch (Exception e) {
            System.err.println("[MonumentalRecipes] Failed to apply vanilla recipe overrides: " + e.getMessage());
        }*/
        //?}
    }

    //? if mc: <1.21 {
    /*@SuppressWarnings("unchecked")
    private static void applyOverrides(MinecraftServer server) throws Exception {
        RecipeManager manager = server.getRecipeManager();
        ClassLoader cl = VanillaRecipeOverrides.class.getClassLoader();

        var manifestStream = cl.getResourceAsStream("vanilla_overrides/manifest.txt");
        if (manifestStream == null) return;

        Field recipesField = RecipeManager.class.getDeclaredField("recipes");
        recipesField.setAccessible(true);
        var originalMap = (Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>>) recipesField.get(manager);
        var mutableMap = new HashMap<RecipeType<?>, Map<ResourceLocation, Recipe<?>>>();
        for (var entry : originalMap.entrySet()) {
            mutableMap.put(entry.getKey(), new HashMap<>(entry.getValue()));
        }

        Field byNameField = RecipeManager.class.getDeclaredField("byName");
        byNameField.setAccessible(true);
        var originalByName = (Map<ResourceLocation, Recipe<?>>) byNameField.get(manager);
        var mutableByName = new HashMap<>(originalByName);

        int replaced = 0;
        int skipped = 0;

        try (var scanner = new Scanner(manifestStream, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                var stream = cl.getResourceAsStream("vanilla_overrides/minecraft/recipes/" + line);
                if (stream == null) continue;

                try (var reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                    if (!allModsPresent(json.toString())) {
                        skipped++;
                        continue;
                    }

                    String recipeName = line.replace(".json", "");
                    ResourceLocation recipeId = new ResourceLocation("minecraft", recipeName);

                    Recipe<?> recipe = RecipeManager.fromJson(recipeId, json,
                        net.minecraftforge.common.crafting.conditions.ICondition.IContext.EMPTY);
                    if (recipe != null) {
                        mutableMap.computeIfAbsent(recipe.getType(), k -> new HashMap<>()).put(recipeId, recipe);
                        mutableByName.put(recipeId, recipe);
                        replaced++;
                    }
                } catch (Exception e) {
                    skipped++;
                }
            }
        }

        recipesField.set(manager, mutableMap);
        byNameField.set(manager, mutableByName);
    }*/
    //?}

    private static boolean allModsPresent(String jsonContent) {
        Set<String> requiredMods = new HashSet<>();
        Matcher matcher = NS_PATTERN.matcher(jsonContent);
        while (matcher.find()) {
            String ns = matcher.group(1);
            if (!SKIP_NS.contains(ns)) {
                requiredMods.add(ns);
            }
        }
        for (String modId : requiredMods) {
            if (!ModList.get().isLoaded(modId)) {
                return false;
            }
        }
        return true;
    }
}
