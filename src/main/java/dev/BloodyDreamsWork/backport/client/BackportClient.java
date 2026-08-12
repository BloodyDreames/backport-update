package dev.BloodyDreamsWork.backport.client;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.client.particle.PoplarLeavesParticle;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModParticles;
import dev.BloodyDreamsWork.backport.registry.ModWoodTypes;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = Backport.MODID, value = Dist.CLIENT)
public final class BackportClient {

    @SubscribeEvent
    @SuppressWarnings("deprecation")
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodTypes.POPLAR);

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POPLAR_DOOR.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POPLAR_TRAPDOOR.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POPLAR_SAPLING.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_POPLAR_SAPLING.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_POPLAR_LEAVES.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_POPLAR_LEAVES.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_POPLAR_LEAVES.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_SHRUB.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHELF_MUSHROOM.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.STRAW_BED.get(), ChunkSectionLayer.CUTOUT);
        });
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CushionRenderer.LAYER, CushionRenderer::createLayer);
        event.registerLayerDefinition(PoplarBoatModelLayers.BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(PoplarBoatModelLayers.CHEST_BOAT, BoatModel::createChestBoatModel);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CUSHION.get(), CushionRenderer::new);
        event.registerEntityRenderer(ModEntities.POPLAR_BOAT.get(),
                context -> new BoatRenderer(context, PoplarBoatModelLayers.BOAT));
        event.registerEntityRenderer(ModEntities.POPLAR_CHEST_BOAT.get(),
                context -> new BoatRenderer(context, PoplarBoatModelLayers.CHEST_BOAT));
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.RED_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        event.registerSpriteSet(ModParticles.ORANGE_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        event.registerSpriteSet(ModParticles.YELLOW_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
    }

    private BackportClient() {
    }
}
