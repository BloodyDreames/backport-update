package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapFunction;
import net.minecraft.core.registries.Registries;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModLootFunctions {
    public static final DeferredRegister<MapCodec<? extends LootItemFunction>> LOOT_FUNCTIONS =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Backport.MODID);

    public static final RegistryObject<MapCodec<ExplorerMapFunction>> EXPLORER_MAP =
            LOOT_FUNCTIONS.register("explorer_map",
                    () -> ExplorerMapFunction.CODEC);

    public static void register(BusGroup modBusGroup) {
        LOOT_FUNCTIONS.register(modBusGroup);
    }

    private ModLootFunctions() {
    }
}
