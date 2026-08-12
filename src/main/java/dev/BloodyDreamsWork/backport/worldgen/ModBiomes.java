package dev.BloodyDreamsWork.backport.worldgen;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public final class ModBiomes {
    public static final ResourceKey<Biome> DAPPLED_FOREST = key("dappled_forest");

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY,
                new ResourceLocation(Backport.MODID, name));
    }

    private ModBiomes() {
    }
}
