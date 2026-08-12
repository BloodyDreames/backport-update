package dev.BloodyDreamsWork.backport.client;

import dev.BloodyDreamsWork.backport.client.particle.PoplarLeavesParticle;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;

public final class BackportClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
                ModBlocks.POPLAR_DOOR.get(), ModBlocks.POPLAR_TRAPDOOR.get(),
                ModBlocks.POPLAR_SAPLING.get(), ModBlocks.POTTED_POPLAR_SAPLING.get(),
                ModBlocks.RED_POPLAR_LEAVES.get(), ModBlocks.ORANGE_POPLAR_LEAVES.get(),
                ModBlocks.YELLOW_POPLAR_LEAVES.get(), ModBlocks.RED_SHRUB.get(),
                ModBlocks.SHELF_MUSHROOM.get(), ModBlocks.STRAW_BED.get());

        EntityModelLayerRegistry.registerModelLayer(CushionRenderer.LAYER, CushionRenderer::createLayer);
        EntityModelLayerRegistry.registerModelLayer(PoplarBoatModelLayers.BOAT, BoatModel::createBoatModel);
        EntityModelLayerRegistry.registerModelLayer(PoplarBoatModelLayers.CHEST_BOAT, BoatModel::createChestBoatModel);
        EntityRendererRegistry.register(ModEntities.CUSHION.get(), CushionRenderer::new);
        EntityRendererRegistry.register(ModEntities.POPLAR_BOAT.get(),
                context -> new BoatRenderer(context, PoplarBoatModelLayers.BOAT));
        EntityRendererRegistry.register(ModEntities.POPLAR_CHEST_BOAT.get(),
                context -> new BoatRenderer(context, PoplarBoatModelLayers.CHEST_BOAT));

        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
        registry.register(ModParticles.RED_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        registry.register(ModParticles.ORANGE_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        registry.register(ModParticles.YELLOW_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
    }
}
