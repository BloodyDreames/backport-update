package dev.BloodyDreamsWork.backport.registry;

import com.mojang.serialization.Codec;
import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapConversionModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Backport.MODID);

    public static final RegistryObject<Codec<ExplorerMapConversionModifier>>
            EXPLORER_MAP_CONVERSION = LOOT_MODIFIERS.register("explorer_map_conversion",
            () -> ExplorerMapConversionModifier.CODEC);

    public static void register(IEventBus modEventBus) {
        LOOT_MODIFIERS.register(modEventBus);
    }

    private ModLootModifiers() {
    }
}
