package dev.BloodyDreamsWork.backport.worldgen;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RED_POPLAR_CHECKED = key("red_poplar_checked");
    public static final ResourceKey<PlacedFeature> ORANGE_POPLAR_CHECKED = key("orange_poplar_checked");
    public static final ResourceKey<PlacedFeature> YELLOW_POPLAR_CHECKED = key("yellow_poplar_checked");
    public static final ResourceKey<PlacedFeature> FALLEN_POPLAR_TREE_CHECKED = key("fallen_poplar_tree_checked");

    public static final ResourceKey<PlacedFeature> BAMBOO_IN_STRUCTURE = key("bamboo_in_structure");

    public static final ResourceKey<PlacedFeature> TREES_DAPPLED_FOREST = key("trees_dappled_forest");
    public static final ResourceKey<PlacedFeature> PATCH_RED_SHRUB = key("patch_red_shrub");
    public static final ResourceKey<PlacedFeature> BROWN_MUSHROOM_DAPPLED_FOREST = key("brown_mushroom_dappled_forest");

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, name));
    }

    private ModPlacedFeatures() {
    }
}
