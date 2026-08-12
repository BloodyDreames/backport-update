package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.CushionItem;
import dev.BloodyDreamsWork.backport.content.ExplorerMapItem;
import dev.BloodyDreamsWork.backport.content.ExplorerMapType;
import net.minecraft.world.item.BlockItem;
import dev.BloodyDreamsWork.backport.content.PoplarBoatItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Backport.MODID);

    public static final DeferredItem<BlockItem> POPLAR_LOG = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_LOG);
    public static final DeferredItem<BlockItem> POPLAR_WOOD = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_POPLAR_LOG = ITEMS.registerSimpleBlockItem(ModBlocks.STRIPPED_POPLAR_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_POPLAR_WOOD = ITEMS.registerSimpleBlockItem(ModBlocks.STRIPPED_POPLAR_WOOD);
    public static final DeferredItem<BlockItem> POPLAR_PLANKS = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_PLANKS);
    public static final DeferredItem<BlockItem> POPLAR_STAIRS = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_STAIRS);
    public static final DeferredItem<BlockItem> POPLAR_SLAB = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_SLAB);
    public static final DeferredItem<BlockItem> POPLAR_FENCE = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_FENCE);
    public static final DeferredItem<BlockItem> POPLAR_FENCE_GATE = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_FENCE_GATE);
    public static final DeferredItem<BlockItem> POPLAR_PRESSURE_PLATE = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> POPLAR_BUTTON = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_BUTTON);
    public static final DeferredItem<BlockItem> POPLAR_TRAPDOOR = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_TRAPDOOR);
    public static final DeferredItem<BlockItem> POPLAR_DOOR = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_DOOR);

    public static final DeferredItem<BlockItem> RED_POPLAR_LEAVES = ITEMS.registerSimpleBlockItem(ModBlocks.RED_POPLAR_LEAVES);
    public static final DeferredItem<BlockItem> ORANGE_POPLAR_LEAVES = ITEMS.registerSimpleBlockItem(ModBlocks.ORANGE_POPLAR_LEAVES);
    public static final DeferredItem<BlockItem> YELLOW_POPLAR_LEAVES = ITEMS.registerSimpleBlockItem(ModBlocks.YELLOW_POPLAR_LEAVES);

    public static final DeferredItem<BlockItem> POPLAR_SAPLING = ITEMS.registerSimpleBlockItem(ModBlocks.POPLAR_SAPLING);

    public static final DeferredItem<SignItem> POPLAR_SIGN = ITEMS.registerItem("poplar_sign",
            props -> new SignItem(ModBlocks.POPLAR_SIGN.get(), ModBlocks.POPLAR_WALL_SIGN.get(), props),
            () -> new Item.Properties().stacksTo(16));

    public static final DeferredItem<HangingSignItem> POPLAR_HANGING_SIGN = ITEMS.registerItem("poplar_hanging_sign",
            props -> new HangingSignItem(ModBlocks.POPLAR_HANGING_SIGN.get(), ModBlocks.POPLAR_WALL_HANGING_SIGN.get(), props),
            () -> new Item.Properties().stacksTo(16));

    public static final DeferredItem<BlockItem> SHELF_MUSHROOM = ITEMS.registerSimpleBlockItem(ModBlocks.SHELF_MUSHROOM);
    public static final DeferredItem<BlockItem> RED_SHRUB = ITEMS.registerSimpleBlockItem(ModBlocks.RED_SHRUB);

    public static final DeferredItem<PoplarBoatItem> POPLAR_BOAT = ITEMS.registerItem("poplar_boat",
            props -> new PoplarBoatItem(false, props), () -> new Item.Properties().stacksTo(1));

    public static final DeferredItem<PoplarBoatItem> POPLAR_CHEST_BOAT = ITEMS.registerItem("poplar_chest_boat",
            props -> new PoplarBoatItem(true, props), () -> new Item.Properties().stacksTo(1));

    public static final DeferredItem<BlockItem> STRAW_BED = ITEMS.registerSimpleBlockItem(ModBlocks.STRAW_BED);

    public static final Map<DyeColor, DeferredItem<CushionItem>> CUSHIONS = cushions();

    public static final Map<ExplorerMapType, DeferredItem<ExplorerMapItem>> EXPLORER_MAPS = explorerMaps();

    public static final Map<DyeColor, DeferredItem<BlockItem>> WOOL_STAIRS =
            blockItems(ModBlocks.WOOL_STAIRS);
    public static final Map<DyeColor, DeferredItem<BlockItem>> WOOL_SLABS =
            blockItems(ModBlocks.WOOL_SLABS);
    public static final Map<DyeColor, DeferredItem<BlockItem>> CONCRETE_STAIRS =
            blockItems(ModBlocks.CONCRETE_STAIRS);
    public static final Map<DyeColor, DeferredItem<BlockItem>> CONCRETE_SLABS =
            blockItems(ModBlocks.CONCRETE_SLABS);

    private static Map<ExplorerMapType, DeferredItem<ExplorerMapItem>> explorerMaps() {
        Map<ExplorerMapType, DeferredItem<ExplorerMapItem>> result = new LinkedHashMap<>();
        for (ExplorerMapType type : ExplorerMapType.values()) {
            result.put(type, ITEMS.registerItem(type.itemName(),
                    props -> new ExplorerMapItem(type, props), () -> new Item.Properties().stacksTo(1)));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, DeferredItem<CushionItem>> cushions() {
        Map<DyeColor, DeferredItem<CushionItem>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, ITEMS.registerItem(color.getName() + "_cushion",
                    props -> new CushionItem(color, props), Item.Properties::new));
        }
        return Collections.unmodifiableMap(result);
    }

    private static <T extends Block> Map<DyeColor, DeferredItem<BlockItem>> blockItems(
            Map<DyeColor, DeferredBlock<T>> blocks) {
        Map<DyeColor, DeferredItem<BlockItem>> result = new LinkedHashMap<>();
        blocks.forEach((color, block) -> result.put(color, ITEMS.registerSimpleBlockItem(block)));
        return Collections.unmodifiableMap(result);
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private ModItems() {
    }
}
