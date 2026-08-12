package dev.BloodyDreamsWork.backport.registry;

import com.mojang.serialization.MapCodec;
import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapCloningRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Backport.MODID);

    private static final ExplorerMapCloningRecipe EXPLORER_MAP_CLONING_RECIPE = new ExplorerMapCloningRecipe();

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ExplorerMapCloningRecipe>>
            EXPLORER_MAP_CLONING = RECIPE_SERIALIZERS.register("explorer_map_cloning",
            () -> new RecipeSerializer<>(MapCodec.unit(EXPLORER_MAP_CLONING_RECIPE),
                    StreamCodec.<RegistryFriendlyByteBuf, ExplorerMapCloningRecipe>unit(EXPLORER_MAP_CLONING_RECIPE)));

    public static void register(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }

    private ModRecipes() {
    }
}
