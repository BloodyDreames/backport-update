package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModStats {
    public static final DeferredRegister<Identifier> CUSTOM_STATS =
            DeferredRegister.create(Registries.CUSTOM_STAT, Backport.MODID);

    public static final DeferredHolder<Identifier, Identifier> SLEEP_IN_STRAW_BED =
            register("sleep_in_straw_bed");

    private static DeferredHolder<Identifier, Identifier> register(String name) {
        return CUSTOM_STATS.register(name,
                () -> Identifier.fromNamespaceAndPath(Backport.MODID, name));
    }

    public static void register(IEventBus modEventBus) {
        CUSTOM_STATS.register(modEventBus);
    }

    private ModStats() {
    }
}
