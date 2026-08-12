package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.content.ExplorerMapCloningRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public final class ModRecipes {
    public static final ModRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            ModRegister.create(BuiltInRegistries.RECIPE_SERIALIZER);

    public static final ModRegister.Entry<CustomRecipe.Serializer<ExplorerMapCloningRecipe>>
            EXPLORER_MAP_CLONING = RECIPE_SERIALIZERS.register("explorer_map_cloning",
            () -> new CustomRecipe.Serializer<>(ExplorerMapCloningRecipe::new));

    public static void register() {
    }

    private ModRecipes() {
    }
}
