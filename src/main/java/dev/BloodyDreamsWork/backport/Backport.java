package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModCreativeTabs;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import dev.BloodyDreamsWork.backport.registry.ModLootFunctions;
import dev.BloodyDreamsWork.backport.registry.ModLootModifiers;
import dev.BloodyDreamsWork.backport.registry.ModMapDecorations;
import dev.BloodyDreamsWork.backport.registry.ModParticles;
import dev.BloodyDreamsWork.backport.registry.ModRecipes;
import dev.BloodyDreamsWork.backport.registry.ModSounds;
import dev.BloodyDreamsWork.backport.registry.ModStats;
import dev.BloodyDreamsWork.backport.worldgen.ModWorldgenTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Backport.MODID)
public class Backport {
    public static final String MODID = "backport";

    public Backport(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModMapDecorations.register(modEventBus);
        ModLootFunctions.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModParticles.register(modEventBus);
        ModSounds.register(modEventBus);
        ModStats.register(modEventBus);
        ModWorldgenTypes.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, BackportConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModBlocks::registerFlowerPots);
        event.enqueueWork(ModBlocks::registerFlammability);
    }
}
