package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModLootFunctions {
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTIONS =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Backport.MODID);

    public static final DeferredHolder<LootItemFunctionType<?>, LootItemFunctionType<ExplorerMapFunction>> EXPLORER_MAP =
            LOOT_FUNCTIONS.register("explorer_map",
                    () -> new LootItemFunctionType<>(ExplorerMapFunction.CODEC));

    public static void register(IEventBus modEventBus) {
        LOOT_FUNCTIONS.register(modEventBus);
    }

    private ModLootFunctions() {
    }
}
