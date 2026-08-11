package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

import java.util.List;

public enum ExplorerMapType {

    ABANDONED_CAMPSITE("abandoned_campsite_map", "abandoned_campsite_map",
            structureTag("abandoned_camp"), MapDecoration.Type.TARGET_X),

    ANCIENT_CITY("ancient_city_map", "ancient_city_map",
            structureTag("on_ancient_city_maps"), MapDecoration.Type.TARGET_X),
    MINESHAFT("mineshaft_map", "mineshaft_map",
            structureTag("on_mineshaft_maps"), MapDecoration.Type.TARGET_X),
    DESERT_PYRAMID("desert_pyramid_map", "desert_pyramid_map",
            structureTag("on_desert_pyramid_maps"), MapDecoration.Type.TARGET_X),
    WARM_OCEAN_RUINS("warm_ocean_ruins_map", "warm_ocean_ruins_map",
            structureTag("on_ocean_ruin_warm_maps"), MapDecoration.Type.TARGET_X),

    OCEAN_EXPLORER("ocean_explorer_map", "ocean_monument_map",
            StructureTags.ON_OCEAN_EXPLORER_MAPS, MapDecoration.Type.MONUMENT),
    WOODLAND_EXPLORER("woodland_explorer_map", "woodland_mansion_map",
            StructureTags.ON_WOODLAND_EXPLORER_MAPS, MapDecoration.Type.MANSION),
    JUNGLE_EXPLORER("jungle_explorer_map", "jungle_temple_map",
            structureTag("on_jungle_explorer_maps"), MapDecoration.Type.TARGET_X),
    SWAMP_EXPLORER("swamp_explorer_map", "swamp_hut_map",
            structureTag("on_swamp_explorer_maps"), MapDecoration.Type.TARGET_X),

    DESERT_VILLAGE("desert_village_map", "desert_village_map",
            structureTag("on_desert_village_maps"), MapDecoration.Type.TARGET_X),
    PLAINS_VILLAGE("plains_village_map", "plains_village_map",
            structureTag("on_plains_village_maps"), MapDecoration.Type.TARGET_X),
    SAVANNA_VILLAGE("savanna_village_map", "savanna_village_map",
            structureTag("on_savanna_village_maps"), MapDecoration.Type.TARGET_X),
    SNOWY_VILLAGE("snowy_village_map", "snowy_village_map",
            structureTag("on_snowy_village_maps"), MapDecoration.Type.TARGET_X),
    TAIGA_VILLAGE("taiga_village_map", "taiga_village_map",
            structureTag("on_taiga_village_maps"), MapDecoration.Type.TARGET_X),

    BURIED_TREASURE("buried_treasure_map", "buried_treasure_map",
            StructureTags.ON_TREASURE_MAPS, MapDecoration.Type.RED_X);

    public static final int SEARCH_RADIUS = 100;

    private final String itemName;
    private final String textureName;
    private final TagKey<Structure> destination;
    private final MapDecoration.Type decoration;

    ExplorerMapType(String itemName, String textureName,
                    TagKey<Structure> destination, MapDecoration.Type decoration) {
        this.itemName = itemName;
        this.textureName = textureName;
        this.destination = destination;
        this.decoration = decoration;
    }

    private static TagKey<Structure> structureTag(String name) {
        return TagKey.create(Registries.STRUCTURE,
                new ResourceLocation(Backport.MODID, name));
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

    public MapDecoration.Type decoration() {
        return this.decoration;
    }

    public static List<ExplorerMapType> cartographerMaps() {
        return List.of(JUNGLE_EXPLORER, SWAMP_EXPLORER,
                DESERT_VILLAGE, PLAINS_VILLAGE, SAVANNA_VILLAGE, SNOWY_VILLAGE, TAIGA_VILLAGE,
                OCEAN_EXPLORER, WOODLAND_EXPLORER);
    }
}
