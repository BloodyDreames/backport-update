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
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Backport.MODID)
public class Backport {
    public static final String MODID = "backport";

    public Backport(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();

        ModBlocks.register(modBusGroup);
        ModItems.register(modBusGroup);
        ModEntities.register(modBusGroup);
        ModMapDecorations.register(modBusGroup);
        ModLootFunctions.register(modBusGroup);
        ModLootModifiers.register(modBusGroup);
        ModRecipes.register(modBusGroup);
        ModParticles.register(modBusGroup);
        ModSounds.register(modBusGroup);
        ModStats.register(modBusGroup);
        ModWorldgenTypes.register(modBusGroup);
        ModCreativeTabs.register(modBusGroup);

        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);

        context.registerConfig(ModConfig.Type.COMMON, BackportConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModBlocks::registerFlowerPots);
        event.enqueueWork(ModBlocks::registerFlammability);
        event.enqueueWork(ModEvents::addSignBlockEntities);
        event.enqueueWork(ModEvents::registerDataMapReplacements);
    }
}
