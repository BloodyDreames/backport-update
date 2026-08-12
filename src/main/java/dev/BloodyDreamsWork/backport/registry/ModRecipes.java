package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapCloningRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Backport.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, CustomRecipe.Serializer<ExplorerMapCloningRecipe>>
            EXPLORER_MAP_CLONING = RECIPE_SERIALIZERS.register("explorer_map_cloning",
            () -> new CustomRecipe.Serializer<>(ExplorerMapCloningRecipe::new));

    public static void register(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }

    private ModRecipes() {
    }
}
