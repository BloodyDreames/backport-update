package dev.BloodyDreamsWork.backport.registry;

import com.mojang.serialization.Codec;
import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapConversionModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Backport.MODID);

    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ExplorerMapConversionModifier>>
            EXPLORER_MAP_CONVERSION = LOOT_MODIFIERS.register("explorer_map_conversion",
            () -> ExplorerMapConversionModifier.CODEC);

    public static void register(IEventBus modEventBus) {
        LOOT_MODIFIERS.register(modEventBus);
    }

    private ModLootModifiers() {
    }
}
