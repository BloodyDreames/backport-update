package dev.BloodyDreamsWork.backport;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = Backport.MODID)
public final class ModGameEvents {
    @SubscribeEvent
    public static void clearStaleTemptation(EntityJoinLevelEvent event) {
        if (!BackportConfig.vanillaBugfixes() || !(event.getEntity() instanceof LivingEntity mob)) {
            return;
        }

        Brain<?> brain = mob.getBrain();
        if (brain.hasMemoryValue(MemoryModuleType.IS_TEMPTED)
                && !brain.hasMemoryValue(MemoryModuleType.TEMPTING_PLAYER)) {
            brain.setMemory(MemoryModuleType.IS_TEMPTED, false);
        }
    }

    private ModGameEvents() {
    }
}
