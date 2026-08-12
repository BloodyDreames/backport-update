package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.registry.ModMapDecorations;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;

import java.util.List;
import java.util.function.Supplier;

public enum ExplorerMapType {

    ABANDONED_CAMPSITE("abandoned_campsite_map", "abandoned_campsite_map",
            structureTag("abandoned_camp"), () -> ModMapDecorations.ABANDONED_CAMP.getHolder().orElseThrow()),

    ANCIENT_CITY("ancient_city_map", "ancient_city_map",
            structureTag("on_ancient_city_maps"), () -> ModMapDecorations.ANCIENT_CITY.getHolder().orElseThrow()),
    MINESHAFT("mineshaft_map", "mineshaft_map",
            structureTag("on_mineshaft_maps"), () -> ModMapDecorations.MINESHAFT.getHolder().orElseThrow()),
    DESERT_PYRAMID("desert_pyramid_map", "desert_pyramid_map",
            structureTag("on_desert_pyramid_maps"), () -> ModMapDecorations.DESERT_PYRAMID.getHolder().orElseThrow()),
    WARM_OCEAN_RUINS("warm_ocean_ruins_map", "warm_ocean_ruins_map",
            structureTag("on_ocean_ruin_warm_maps"), () -> MapDecorationTypes.TARGET_X),

    OCEAN_EXPLORER("ocean_explorer_map", "ocean_monument_map",
            StructureTags.ON_OCEAN_EXPLORER_MAPS, () -> MapDecorationTypes.OCEAN_MONUMENT),
    WOODLAND_EXPLORER("woodland_explorer_map", "woodland_mansion_map",
            StructureTags.ON_WOODLAND_EXPLORER_MAPS, () -> MapDecorationTypes.WOODLAND_MANSION),
    TRIAL_EXPLORER("trial_explorer_map", "trial_chamber_map",
            StructureTags.ON_TRIAL_CHAMBERS_MAPS, () -> MapDecorationTypes.TRIAL_CHAMBERS),
    JUNGLE_EXPLORER("jungle_explorer_map", "jungle_temple_map",
            StructureTags.ON_JUNGLE_EXPLORER_MAPS, () -> MapDecorationTypes.JUNGLE_TEMPLE),
    SWAMP_EXPLORER("swamp_explorer_map", "swamp_hut_map",
            StructureTags.ON_SWAMP_EXPLORER_MAPS, () -> MapDecorationTypes.SWAMP_HUT),

    DESERT_VILLAGE("desert_village_map", "desert_village_map",
            StructureTags.ON_DESERT_VILLAGE_MAPS, () -> MapDecorationTypes.DESERT_VILLAGE),
    PLAINS_VILLAGE("plains_village_map", "plains_village_map",
            StructureTags.ON_PLAINS_VILLAGE_MAPS, () -> MapDecorationTypes.PLAINS_VILLAGE),
    SAVANNA_VILLAGE("savanna_village_map", "savanna_village_map",
            StructureTags.ON_SAVANNA_VILLAGE_MAPS, () -> MapDecorationTypes.SAVANNA_VILLAGE),
    SNOWY_VILLAGE("snowy_village_map", "snowy_village_map",
            StructureTags.ON_SNOWY_VILLAGE_MAPS, () -> MapDecorationTypes.SNOWY_VILLAGE),
    TAIGA_VILLAGE("taiga_village_map", "taiga_village_map",
            StructureTags.ON_TAIGA_VILLAGE_MAPS, () -> MapDecorationTypes.TAIGA_VILLAGE),

    BURIED_TREASURE("buried_treasure_map", "buried_treasure_map",
            StructureTags.ON_TREASURE_MAPS, () -> MapDecorationTypes.RED_X);

    public static final int SEARCH_RADIUS = 100;

    private final String itemName;
    private final String textureName;
    private final TagKey<Structure> destination;
    private final Supplier<Holder<MapDecorationType>> decoration;

    ExplorerMapType(String itemName, String textureName,
                    TagKey<Structure> destination, Supplier<Holder<MapDecorationType>> decoration) {
        this.itemName = itemName;
        this.textureName = textureName;
        this.destination = destination;
        this.decoration = decoration;
    }

    private static TagKey<Structure> structureTag(String name) {
        return TagKey.create(Registries.STRUCTURE,
                Identifier.fromNamespaceAndPath(Backport.MODID, name));
    }

    public String itemName() {
        return this.itemName;
    }

    public String textureName() {
        return this.textureName;
    }

    public TagKey<Structure> destination() {
        return this.destination;
    }

    public Holder<MapDecorationType> decoration() {
        return this.decoration.get();
    }

    public static List<ExplorerMapType> cartographerMaps() {
        return List.of(JUNGLE_EXPLORER, SWAMP_EXPLORER,
                DESERT_VILLAGE, PLAINS_VILLAGE, SAVANNA_VILLAGE, SNOWY_VILLAGE, TAIGA_VILLAGE,
                OCEAN_EXPLORER, TRIAL_EXPLORER, WOODLAND_EXPLORER);
    }
}
