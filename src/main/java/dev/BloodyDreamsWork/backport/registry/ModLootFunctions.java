package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapConversionFunction;
import dev.BloodyDreamsWork.backport.content.ExplorerMapFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModLootFunctions {
    public static final ModRegister<LootItemFunctionType<?>> LOOT_FUNCTIONS =
            ModRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE);

    public static final ModRegister.Entry<LootItemFunctionType<ExplorerMapFunction>> EXPLORER_MAP =
            LOOT_FUNCTIONS.register("explorer_map",
                    () -> new LootItemFunctionType<>(ExplorerMapFunction.CODEC));

    public static final ModRegister.Entry<LootItemFunctionType<ExplorerMapConversionFunction>> EXPLORER_MAP_CONVERSION =
            LOOT_FUNCTIONS.register("explorer_map_conversion",
                    () -> new LootItemFunctionType<>(ExplorerMapConversionFunction.CODEC));

    public static void register() {
    }

    private ModLootFunctions() {
    }
}
