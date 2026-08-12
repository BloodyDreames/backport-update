package dev.BloodyDreamsWork.backport.registry;

import com.mojang.serialization.MapCodec;
import dev.BloodyDreamsWork.backport.content.ExplorerMapConversionFunction;
import dev.BloodyDreamsWork.backport.content.ExplorerMapFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public final class ModLootFunctions {
    public static final ModRegister<MapCodec<? extends LootItemFunction>> LOOT_FUNCTIONS =
            ModRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE);

    public static final ModRegister.Entry<MapCodec<ExplorerMapFunction>> EXPLORER_MAP =
            LOOT_FUNCTIONS.register("explorer_map", () -> ExplorerMapFunction.CODEC);

    public static final ModRegister.Entry<MapCodec<ExplorerMapConversionFunction>> EXPLORER_MAP_CONVERSION =
            LOOT_FUNCTIONS.register("explorer_map_conversion", () -> ExplorerMapConversionFunction.CODEC);

    public static void register() {
    }

    private ModLootFunctions() {
    }
}
