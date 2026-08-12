package dev.BloodyDreamsWork.backport.data;

import dev.BloodyDreamsWork.backport.Backport;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Backport.MODID)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        event.addProvider(new ModModelProvider(event.getGenerator().getPackOutput()));
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        event.addProvider(new ModBlockTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new ModItemTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));

        event.addProvider(new ModRecipeProvider.Runner(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(ModLootTableProvider.create(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new ModLootModifierProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));

        DatapackBuiltinEntriesProvider datapack = ModDatapackProvider.create(event.getGenerator().getPackOutput(), event.getLookupProvider());
        event.addProvider(datapack);
        event.addProvider(new ModBiomeTagsProvider(event.getGenerator().getPackOutput(), datapack.getRegistryProvider()));
    }
}
