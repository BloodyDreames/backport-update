package dev.BloodyDreamsWork.backport.client;

import dev.BloodyDreamsWork.backport.client.particle.PoplarLeavesParticle;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;

public final class BackportClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(CushionRenderer.LAYER, CushionRenderer::createLayer);
        ModelLayerRegistry.registerModelLayer(PoplarBoatModelLayers.BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(PoplarBoatModelLayers.CHEST_BOAT, BoatModel::createChestBoatModel);
        EntityRendererRegistry.register(ModEntities.CUSHION.get(), CushionRenderer::new);
        EntityRendererRegistry.register(ModEntities.POPLAR_BOAT.get(),
                context -> new BoatRenderer(context, PoplarBoatModelLayers.BOAT));
        EntityRendererRegistry.register(ModEntities.POPLAR_CHEST_BOAT.get(),
                context -> new BoatRenderer(context, PoplarBoatModelLayers.CHEST_BOAT));

        ParticleProviderRegistry registry = ParticleProviderRegistry.getInstance();
        registry.register(ModParticles.RED_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        registry.register(ModParticles.ORANGE_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
        registry.register(ModParticles.YELLOW_POPLAR_LEAVES.get(), PoplarLeavesParticle.Provider::new);
    }
}
