package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModMapDecorations {
    public static final ModRegister<MapDecorationType> MAP_DECORATIONS =
            ModRegister.create(BuiltInRegistries.MAP_DECORATION_TYPE);

    public static final ModRegister.Entry<MapDecorationType> ABANDONED_CAMP =
            marker("abandoned_camp");

    public static final ModRegister.Entry<MapDecorationType> ANCIENT_CITY =
            marker("ancient_city");
    public static final ModRegister.Entry<MapDecorationType> DESERT_PYRAMID =
            marker("desert_pyramid");
    public static final ModRegister.Entry<MapDecorationType> MINESHAFT =
            marker("mineshaft");

    private static ModRegister.Entry<MapDecorationType> marker(String name) {
        return MAP_DECORATIONS.register(name, () -> new MapDecorationType(
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, name),
                true,
                MapColor.COLOR_LIGHT_GRAY.col,
                true,
                false));
    }

    public static void register() {
    }

    private ModMapDecorations() {
    }
}
