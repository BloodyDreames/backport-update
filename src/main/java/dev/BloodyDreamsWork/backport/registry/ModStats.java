package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModStats {
    public static final ModRegister<Identifier> CUSTOM_STATS =
            ModRegister.create(BuiltInRegistries.CUSTOM_STAT);

    public static final ModRegister.Entry<Identifier> SLEEP_IN_STRAW_BED =
            register("sleep_in_straw_bed");

    private static ModRegister.Entry<Identifier> register(String name) {
        return CUSTOM_STATS.register(name,
                () -> Identifier.fromNamespaceAndPath(Backport.MODID, name));
    }

    public static void register() {
    }

    private ModStats() {
    }
}
