package dev.BloodyDreamsWork.backport.client;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.client.particle.PoplarLeavesParticle;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModParticles;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = Backport.MODID, value = Dist.CLIENT)
public final class BackportClient {
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
