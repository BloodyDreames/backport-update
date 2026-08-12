package dev.BloodyDreamsWork.backport.mixin;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.world.entity.npc.VillagerTrades$TreasureMapForEmeralds")
public interface TreasureMapForEmeraldsAccessor {

    @Accessor("destination")
    TagKey<Structure> backport$destination();

    @Accessor("displayName")
    String backport$displayName();

    @Accessor("destinationType")
    MapDecoration.Type backport$destinationType();

    @Accessor("emeraldCost")
    int backport$emeraldCost();

    @Accessor("maxUses")
    int backport$maxUses();

    @Accessor("villagerXp")
    int backport$villagerXp();
}
