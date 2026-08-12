package dev.BloodyDreamsWork.backport.registry;

import com.mojang.serialization.MapCodec;
import dev.BloodyDreamsWork.backport.content.ExplorerMapCloningRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public final class ModRecipes {
    public static final ModRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            ModRegister.create(BuiltInRegistries.RECIPE_SERIALIZER);

    private static final ExplorerMapCloningRecipe EXPLORER_MAP_CLONING_RECIPE =
            new ExplorerMapCloningRecipe();

    public static final ModRegister.Entry<RecipeSerializer<ExplorerMapCloningRecipe>> EXPLORER_MAP_CLONING =
            RECIPE_SERIALIZERS.register("explorer_map_cloning",
                    () -> new RecipeSerializer<>(MapCodec.unit(EXPLORER_MAP_CLONING_RECIPE),
                            StreamCodec.<RegistryFriendlyByteBuf, ExplorerMapCloningRecipe>unit(
                                    EXPLORER_MAP_CLONING_RECIPE)));

    public static void register() {
    }

    private ModRecipes() {
    }
}
