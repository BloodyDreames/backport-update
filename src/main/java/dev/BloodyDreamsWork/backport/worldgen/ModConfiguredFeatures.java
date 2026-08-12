package dev.BloodyDreamsWork.backport.worldgen;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public final class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_POPLAR = key("red_poplar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_POPLAR = key("orange_poplar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_POPLAR = key("yellow_poplar");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_POPLAR_TREE = key("fallen_poplar_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SHRUB = key("red_shrub");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_DAPPLED_FOREST = key("trees_dappled_forest");

    public static final ResourceKey<ConfiguredFeature<?, ?>>[] POPLAR_VARIANTS = variants();

    @SuppressWarnings("unchecked")
    private static ResourceKey<ConfiguredFeature<?, ?>>[] variants() {
        return new ResourceKey[]{RED_POPLAR, ORANGE_POPLAR, YELLOW_POPLAR};
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                Identifier.fromNamespaceAndPath(Backport.MODID, name));
    }

    private ModConfiguredFeatures() {
    }
}
