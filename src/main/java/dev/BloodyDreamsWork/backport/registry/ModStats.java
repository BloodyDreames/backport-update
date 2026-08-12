package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModStats {
    public static final DeferredRegister<Identifier> CUSTOM_STATS =
            DeferredRegister.create(Registries.CUSTOM_STAT, Backport.MODID);

    public static final RegistryObject<Identifier> SLEEP_IN_STRAW_BED =
            register("sleep_in_straw_bed");

    private static RegistryObject<Identifier> register(String name) {
        return CUSTOM_STATS.register(name,
                () -> Identifier.fromNamespaceAndPath(Backport.MODID, name));
    }

    public static void register(BusGroup modBusGroup) {
        CUSTOM_STATS.register(modBusGroup);
    }

    private ModStats() {
    }
}
