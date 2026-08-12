package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import javax.annotation.Nullable;
import java.util.Optional;

public record ExplorerMapForEmeralds(ExplorerMapType type, String displayName,
                                     int emeraldCost, int maxUses, int villagerXp)
        implements VillagerTrades.ItemListing {

    private static final float PRICE_MULTIPLIER = 0.2F;

    private static final byte ZOOM = 2;

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity trader, RandomSource random) {
        if (!(trader.level() instanceof ServerLevel level)) {
            return null;
        }

        BlockPos found = level.findNearestMapStructure(
                this.type.destination(), trader.blockPosition(), ExplorerMapType.SEARCH_RADIUS, true);
        if (found == null) {
            return null;
        }

        ItemStack filled = MapItem.create(level, found.getX(), found.getZ(), ZOOM, true, true);
        MapItem.renderBiomePreviewMap(level, filled);
        MapItemSavedData.addTargetDecoration(filled, found, "+", this.type.decoration());

        ItemStack map = new ItemStack(ModItems.EXPLORER_MAPS.get(this.type).get());
        map.set(DataComponents.MAP_ID, filled.get(DataComponents.MAP_ID));
        map.set(DataComponents.MAP_DECORATIONS, filled.get(DataComponents.MAP_DECORATIONS));
        map.set(DataComponents.ITEM_NAME, Component.translatable(this.displayName));

        return new MerchantOffer(
                new ItemCost(Items.EMERALD, this.emeraldCost),
                Optional.of(new ItemCost(Items.COMPASS)),
                map, this.maxUses, this.villagerXp, PRICE_MULTIPLIER);
    }
}
