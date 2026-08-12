package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.content.ExplorerMapConversionFunction;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

public final class ModGameEvents {

    public static void register() {
        clearStaleTemptation();
        convertVanillaMaps();
    }

    private static void convertVanillaMaps() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin() && key.identifier().getNamespace().equals("minecraft")) {
                tableBuilder.apply(ExplorerMapConversionFunction.INSTANCE);
            }
        });
    }

    private static void clearStaleTemptation() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, level) -> {
            if (!BackportConfig.vanillaBugfixes() || !(entity instanceof LivingEntity mob)) {
                return;
            }

            Brain<?> brain = mob.getBrain();
            if (brain.hasMemoryValue(MemoryModuleType.IS_TEMPTED)
                    && !brain.hasMemoryValue(MemoryModuleType.TEMPTING_PLAYER)) {
                brain.setMemory(MemoryModuleType.IS_TEMPTED, false);
            }
        });
    }

    private ModGameEvents() {
    }
}
