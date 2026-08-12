package dev.BloodyDreamsWork.backport.client;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.client.particle.PoplarLeavesParticle;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModParticles;
import dev.BloodyDreamsWork.backport.registry.ModWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = Backport.MODID, value = Dist.CLIENT)
public final class BackportClient {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> Sheets.addWoodType(ModWoodTypes.POPLAR));
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CushionRenderer.LAYER, CushionRenderer::createLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CUSHION.get(), CushionRenderer::new);
        event.registerEntityRenderer(ModEntities.POPLAR_BOAT.get(),
                context -> new PoplarBoatRenderer(context, false));
        event.registerEntityRenderer(ModEntities.POPLAR_CHEST_BOAT.get(),
                context -> new PoplarBoatRenderer(context, true));
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
