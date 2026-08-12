package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.content.ExplorerMapConversionFunction;
import dev.BloodyDreamsWork.backport.content.ExplorerMapForEmeralds;
import dev.BloodyDreamsWork.backport.content.ExplorerMapType;
import dev.BloodyDreamsWork.backport.mixin.TreasureMapForEmeraldsAccessor;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Map;

public final class ModGameEvents {

    private static final Map<TagKey<Structure>, ExplorerMapType> REPLACEMENTS = Map.of(
            StructureTags.ON_OCEAN_EXPLORER_MAPS, ExplorerMapType.OCEAN_EXPLORER,
            StructureTags.ON_WOODLAND_EXPLORER_MAPS, ExplorerMapType.WOODLAND_EXPLORER,
            StructureTags.ON_TRIAL_CHAMBERS_MAPS, ExplorerMapType.TRIAL_EXPLORER);

    public static void register() {
        replaceCartographerMaps();
        addWanderingTraderOffers();
        clearStaleTemptation();
        convertVanillaMaps();
    }

    private static void convertVanillaMaps() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && key.identifier().getNamespace().equals("minecraft")) {
                tableBuilder.apply(() -> ExplorerMapConversionFunction.INSTANCE);
            }
        });
    }

    private static void replaceCartographerMaps() {
        var byLevel = VillagerTrades.TRADES.get(VillagerProfession.CARTOGRAPHER);
        if (byLevel == null) {
            return;
        }

        byLevel.values().forEach(listings -> {
            for (int i = 0; i < listings.length; i++) {
                if (!(listings[i] instanceof VillagerTrades.TreasureMapForEmeralds vanilla)) {
                    continue;
                }
                TreasureMapForEmeraldsAccessor fields = (TreasureMapForEmeraldsAccessor) vanilla;
                ExplorerMapType type = REPLACEMENTS.get(fields.backport$destination());
                if (type != null) {
                    listings[i] = new ExplorerMapForEmeralds(type,
                            fields.backport$displayName(),
                            fields.backport$emeraldCost(),
                            fields.backport$maxUses(),
                            fields.backport$villagerXp());
                }
            }
        });
    }

    private static void addWanderingTraderOffers() {
        TradeOfferHelper.registerWanderingTraderOffers(builder -> {
            if (!BackportConfig.wanderingTraderOffers()) {
                return;
            }
            builder.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                    new VillagerTrades.ItemsForEmeralds(ModItems.POPLAR_SAPLING.get(), 5, 1, 8, 1),
                    new VillagerTrades.ItemsForEmeralds(ModItems.SHELF_MUSHROOM.get(), 1, 1, 12, 1));
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
