package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.content.ExplorerMapForEmeralds;
import dev.BloodyDreamsWork.backport.content.ExplorerMapType;
import dev.BloodyDreamsWork.backport.mixin.TreasureMapForEmeraldsAccessor;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Backport.MODID)
public final class ModGameEvents {

    private static final Map<TagKey<Structure>, ExplorerMapType> REPLACEMENTS = Map.of(
            StructureTags.ON_OCEAN_EXPLORER_MAPS, ExplorerMapType.OCEAN_EXPLORER,
            StructureTags.ON_WOODLAND_EXPLORER_MAPS, ExplorerMapType.WOODLAND_EXPLORER,
            StructureTags.ON_TRIAL_CHAMBERS_MAPS, ExplorerMapType.TRIAL_EXPLORER);

    @SubscribeEvent
    public static void replaceCartographerMaps(VillagerTradesEvent event) {
        if (event.getType() != VillagerProfession.CARTOGRAPHER) {
            return;
        }

        event.getTrades().values().forEach(listings -> {
            for (int i = 0; i < listings.size(); i++) {
                if (!(listings.get(i) instanceof VillagerTrades.TreasureMapForEmeralds vanilla)) {
                    continue;
                }
                TreasureMapForEmeraldsAccessor fields = (TreasureMapForEmeraldsAccessor) vanilla;
                ExplorerMapType type = REPLACEMENTS.get(fields.backport$destination());
                if (type != null) {
                    listings.set(i, new ExplorerMapForEmeralds(type,
                            fields.backport$displayName(),
                            fields.backport$emeraldCost(),
                            fields.backport$maxUses(),
                            fields.backport$villagerXp()));
                }
            }
        });
    }

    @SubscribeEvent
    public static void addWanderingTraderOffers(WandererTradesEvent event) {
        if (!BackportConfig.wanderingTraderOffers()) {
            return;
        }

        event.getGenericTrades().add(new VillagerTrades.ItemsForEmeralds(
                ModItems.POPLAR_SAPLING.get(), 5, 1, 8, 1));
        event.getGenericTrades().add(new VillagerTrades.ItemsForEmeralds(
                ModItems.SHELF_MUSHROOM.get(), 1, 1, 12, 1));
    }

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
