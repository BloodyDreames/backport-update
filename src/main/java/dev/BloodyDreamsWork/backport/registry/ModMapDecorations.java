package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModMapDecorations {
    public static final DeferredRegister<MapDecorationType> MAP_DECORATIONS =
            DeferredRegister.create(Registries.MAP_DECORATION_TYPE, Backport.MODID);

    public static final DeferredHolder<MapDecorationType, MapDecorationType> ABANDONED_CAMP =
            marker("abandoned_camp");

    public static final DeferredHolder<MapDecorationType, MapDecorationType> ANCIENT_CITY =
            marker("ancient_city");
    public static final DeferredHolder<MapDecorationType, MapDecorationType> DESERT_PYRAMID =
            marker("desert_pyramid");
    public static final DeferredHolder<MapDecorationType, MapDecorationType> MINESHAFT =
            marker("mineshaft");

    private static DeferredHolder<MapDecorationType, MapDecorationType> marker(String name) {
        return MAP_DECORATIONS.register(name, () -> new MapDecorationType(
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, name),
                true,
                MapColor.COLOR_LIGHT_GRAY.col,
                true,
                false));
    }

    public static void register(IEventBus modEventBus) {
        MAP_DECORATIONS.register(modEventBus);
    }

    private ModMapDecorations() {
    }
}
