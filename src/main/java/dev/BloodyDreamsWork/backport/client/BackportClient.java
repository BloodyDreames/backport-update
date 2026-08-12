package dev.BloodyDreamsWork.backport.client;

import dev.BloodyDreamsWork.backport.client.particle.PoplarLeavesParticle;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModParticles;
import dev.BloodyDreamsWork.backport.registry.ModWoodTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public final class BackportClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModWoodTypes.touch();

        registerRenderLayers();
        registerRenderers();
        registerParticleProviders();
    }

    private static void registerRenderLayers() {
        cutout(ModBlocks.POPLAR_DOOR.get(),
                ModBlocks.POPLAR_TRAPDOOR.get(),
                ModBlocks.POPLAR_SAPLING.get(),
                ModBlocks.POTTED_POPLAR_SAPLING.get(),
                ModBlocks.RED_SHRUB.get(),
                ModBlocks.SHELF_MUSHROOM.get(),
                ModBlocks.STRAW_BED.get());

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(),
                ModBlocks.RED_POPLAR_LEAVES.get(),
                ModBlocks.ORANGE_POPLAR_LEAVES.get(),
                ModBlocks.YELLOW_POPLAR_LEAVES.get());
    }

    private static void cutout(Block... blocks) {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), blocks);
    }

    private static void registerRenderers() {
        EntityModelLayerRegistry.registerModelLayer(CushionRenderer.LAYER, CushionRenderer::createLayer);
        EntityRendererRegistry.register(ModEntities.CUSHION.get(), CushionRenderer::new);
        EntityRendererRegistry.register(ModEntities.POPLAR_BOAT.get(),
                context -> new PoplarBoatRenderer(context, false));
        EntityRendererRegistry.register(ModEntities.POPLAR_CHEST_BOAT.get(),
                context -> new PoplarBoatRenderer(context, true));
    }

    private static void registerParticleProviders() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
        registry.register(ModParticles.RED_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        registry.register(ModParticles.ORANGE_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        registry.register(ModParticles.YELLOW_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
    }
}
