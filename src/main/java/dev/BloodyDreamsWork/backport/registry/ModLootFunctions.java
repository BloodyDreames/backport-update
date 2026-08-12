package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModLootFunctions {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Backport.MODID);

    public static final RegistryObject<LootItemFunctionType> EXPLORER_MAP =
            LOOT_FUNCTIONS.register("explorer_map",
                    () -> new LootItemFunctionType(new ExplorerMapFunction.Serializer()));

    public static void register(IEventBus modEventBus) {
        LOOT_FUNCTIONS.register(modEventBus);
    }

    private ModLootFunctions() {
    }
}
