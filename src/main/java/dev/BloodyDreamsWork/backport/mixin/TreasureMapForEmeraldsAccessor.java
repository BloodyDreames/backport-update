package dev.BloodyDreamsWork.backport.mixin;

import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(VillagerTrades.TreasureMapForEmeralds.class)
public interface TreasureMapForEmeraldsAccessor {

    @Accessor("destination")
    TagKey<Structure> backport$destination();

    @Accessor("displayName")
    String backport$displayName();

    @Accessor("destinationType")
    Holder<MapDecorationType> backport$destinationType();

    @Accessor("emeraldCost")
    int backport$emeraldCost();

    @Accessor("maxUses")
    int backport$maxUses();

    @Accessor("villagerXp")
    int backport$villagerXp();
}
