package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ModTags {

    public static final class Blocks {
        public static final TagKey<Block> POPLAR_LOGS = tag("poplar_logs");
        public static final TagKey<Block> WOOL_STAIRS = tag("wool_stairs");
        public static final TagKey<Block> WOOL_SLABS = tag("wool_slabs");
        public static final TagKey<Block> CONCRETE_STAIRS = tag("concrete_stairs");
        public static final TagKey<Block> CONCRETE_SLABS = tag("concrete_slabs");

        public static final TagKey<Block> CUSHION_USES_COLLISION_SHAPE = tag("cushion_uses_collision_shape");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registry.BLOCK_REGISTRY,
                    new ResourceLocation(Backport.MODID, name));
        }

        private Blocks() {
        }
    }

    public static final class Items {
        public static final TagKey<Item> POPLAR_LOGS = tag("poplar_logs");

        public static final TagKey<Item> MUSHROOMS = tag("mushrooms");

        public static final TagKey<Item> WOOL_STAIRS = tag("wool_stairs");
        public static final TagKey<Item> WOOL_SLABS = tag("wool_slabs");
        public static final TagKey<Item> CONCRETE_STAIRS = tag("concrete_stairs");
        public static final TagKey<Item> CONCRETE_SLABS = tag("concrete_slabs");

        public static final TagKey<Item> CLONEABLE_MAPS = tag("cloneable_maps");

        public static final TagKey<Item> EXTENDABLE_MAPS = tag("extendable_maps");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registry.ITEM_REGISTRY,
                    new ResourceLocation(Backport.MODID, name));
        }

        private Items() {
        }
    }

    private ModTags() {
    }
}
