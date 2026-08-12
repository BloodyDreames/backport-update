package dev.BloodyDreamsWork.backport.data;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Backport.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModModelProvider(output));

        generator.addProvider(event.includeServer(),
                new ModBlockTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(),
                new ModItemTagsProvider(output, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new ModRecipeProvider.Runner(output, lookupProvider));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output, lookupProvider));
        generator.addProvider(event.includeServer(), new ModLootModifierProvider(output, lookupProvider));

        DatapackBuiltinEntriesProvider datapack = ModDatapackProvider.create(output, lookupProvider);
        generator.addProvider(event.includeServer(), datapack);
        generator.addProvider(event.includeServer(),
                new ModBiomeTagsProvider(output, datapack.getFullRegistries(), existingFileHelper));
    }
}
