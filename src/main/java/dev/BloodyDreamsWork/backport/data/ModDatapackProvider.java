package dev.BloodyDreamsWork.backport.data;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.worldgen.ModWorldgenBootstrap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public final class ModDatapackProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModWorldgenBootstrap::configuredFeatures)
            .add(Registries.PLACED_FEATURE, ModWorldgenBootstrap::placedFeatures)
            .add(Registries.BIOME, ModWorldgenBootstrap::biomes);

    public static DatapackBuiltinEntriesProvider create(PackOutput output,
                                                        CompletableFuture<HolderLookup.Provider> registries) {
        return new DatapackBuiltinEntriesProvider(output, registries, BUILDER, Set.of(Backport.MODID));
    }

    private ModDatapackProvider() {
    }
}
