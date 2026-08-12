package dev.BloodyDreamsWork.backport.data;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Backport.MODID)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(),
                new ModItemModelProvider(output, existingFileHelper));

        ModBlockTagsProvider blockTags =
                new ModBlockTagsProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(
                output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModRecipeProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output, lookupProvider));
        generator.addProvider(event.includeServer(),
                new ModLootModifierProvider(output, lookupProvider));

        DatapackBuiltinEntriesProvider datapack = ModDatapackProvider.create(output, lookupProvider);
        generator.addProvider(event.includeServer(), datapack);
        generator.addProvider(event.includeServer(), new ModBiomeTagsProvider(
                output, datapack.getRegistryProvider(), existingFileHelper));
    }
}
