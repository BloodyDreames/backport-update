package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModStats {
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS =
            DeferredRegister.create(Registry.CUSTOM_STAT_REGISTRY, Backport.MODID);

    public static final RegistryObject<ResourceLocation> SLEEP_IN_STRAW_BED =
            register("sleep_in_straw_bed");

    private static RegistryObject<ResourceLocation> register(String name) {
        return CUSTOM_STATS.register(name,
                () -> new ResourceLocation(Backport.MODID, name));
    }

    public static void register(IEventBus modEventBus) {
        CUSTOM_STATS.register(modEventBus);
    }

    private ModStats() {
    }
}
