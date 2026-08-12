package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapCloningRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModRecipes {
    public static final ModRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            ModRegister.create(BuiltInRegistries.RECIPE_SERIALIZER);

    public static final ModRegister.Entry<SimpleCraftingRecipeSerializer<ExplorerMapCloningRecipe>>
            EXPLORER_MAP_CLONING = RECIPE_SERIALIZERS.register("explorer_map_cloning",
            () -> new SimpleCraftingRecipeSerializer<>(ExplorerMapCloningRecipe::new));

    public static void register() {
    }

    private ModRecipes() {
    }
}
