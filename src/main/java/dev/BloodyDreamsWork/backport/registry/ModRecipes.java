package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapCloningRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registry.RECIPE_SERIALIZER_REGISTRY, Backport.MODID);

    public static final RegistryObject<SimpleRecipeSerializer<ExplorerMapCloningRecipe>>
            EXPLORER_MAP_CLONING = RECIPE_SERIALIZERS.register("explorer_map_cloning",
            () -> new SimpleRecipeSerializer<>(ExplorerMapCloningRecipe::new));

    public static void register(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }

    private ModRecipes() {
    }
}
