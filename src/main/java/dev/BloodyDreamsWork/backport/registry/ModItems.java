package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.CushionItem;
import dev.BloodyDreamsWork.backport.content.ExplorerMapItem;
import dev.BloodyDreamsWork.backport.content.ExplorerMapType;
import net.minecraft.world.item.BlockItem;
import dev.BloodyDreamsWork.backport.content.PoplarBoatItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Backport.MODID);

    private static DeferredItem<BlockItem> simpleBlockItem(DeferredBlock<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(),
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final DeferredItem<BlockItem> POPLAR_LOG = simpleBlockItem(ModBlocks.POPLAR_LOG);
    public static final DeferredItem<BlockItem> POPLAR_WOOD = simpleBlockItem(ModBlocks.POPLAR_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_POPLAR_LOG = simpleBlockItem(ModBlocks.STRIPPED_POPLAR_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_POPLAR_WOOD = simpleBlockItem(ModBlocks.STRIPPED_POPLAR_WOOD);
    public static final DeferredItem<BlockItem> POPLAR_PLANKS = simpleBlockItem(ModBlocks.POPLAR_PLANKS);
    public static final DeferredItem<BlockItem> POPLAR_STAIRS = simpleBlockItem(ModBlocks.POPLAR_STAIRS);
    public static final DeferredItem<BlockItem> POPLAR_SLAB = simpleBlockItem(ModBlocks.POPLAR_SLAB);
    public static final DeferredItem<BlockItem> POPLAR_FENCE = simpleBlockItem(ModBlocks.POPLAR_FENCE);
    public static final DeferredItem<BlockItem> POPLAR_FENCE_GATE = simpleBlockItem(ModBlocks.POPLAR_FENCE_GATE);
    public static final DeferredItem<BlockItem> POPLAR_PRESSURE_PLATE = simpleBlockItem(ModBlocks.POPLAR_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> POPLAR_BUTTON = simpleBlockItem(ModBlocks.POPLAR_BUTTON);
    public static final DeferredItem<BlockItem> POPLAR_TRAPDOOR = simpleBlockItem(ModBlocks.POPLAR_TRAPDOOR);
    public static final DeferredItem<BlockItem> POPLAR_DOOR = simpleBlockItem(ModBlocks.POPLAR_DOOR);

    public static final DeferredItem<BlockItem> RED_POPLAR_LEAVES = simpleBlockItem(ModBlocks.RED_POPLAR_LEAVES);
    public static final DeferredItem<BlockItem> ORANGE_POPLAR_LEAVES = simpleBlockItem(ModBlocks.ORANGE_POPLAR_LEAVES);
    public static final DeferredItem<BlockItem> YELLOW_POPLAR_LEAVES = simpleBlockItem(ModBlocks.YELLOW_POPLAR_LEAVES);

    public static final DeferredItem<BlockItem> POPLAR_SAPLING = simpleBlockItem(ModBlocks.POPLAR_SAPLING);

    public static final DeferredItem<SignItem> POPLAR_SIGN = ITEMS.register("poplar_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16),
                    ModBlocks.POPLAR_SIGN.get(), ModBlocks.POPLAR_WALL_SIGN.get()));

    public static final DeferredItem<HangingSignItem> POPLAR_HANGING_SIGN = ITEMS.register("poplar_hanging_sign",
            () -> new HangingSignItem(ModBlocks.POPLAR_HANGING_SIGN.get(), ModBlocks.POPLAR_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static final DeferredItem<BlockItem> SHELF_MUSHROOM = simpleBlockItem(ModBlocks.SHELF_MUSHROOM);
    public static final DeferredItem<BlockItem> RED_SHRUB = simpleBlockItem(ModBlocks.RED_SHRUB);

    public static final DeferredItem<PoplarBoatItem> POPLAR_BOAT = ITEMS.register("poplar_boat",
            () -> new PoplarBoatItem(false, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<PoplarBoatItem> POPLAR_CHEST_BOAT = ITEMS.register("poplar_chest_boat",
            () -> new PoplarBoatItem(true, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<BlockItem> STRAW_BED = simpleBlockItem(ModBlocks.STRAW_BED);

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
            result.put(type, ITEMS.register(type.itemName(),
                    () -> new ExplorerMapItem(type, new Item.Properties().stacksTo(1))));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, DeferredItem<CushionItem>> cushions() {
        Map<DyeColor, DeferredItem<CushionItem>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, ITEMS.register(color.getName() + "_cushion",
                    () -> new CushionItem(color, new Item.Properties())));
        }
        return Collections.unmodifiableMap(result);
    }

    private static <T extends Block> Map<DyeColor, DeferredItem<BlockItem>> blockItems(
            Map<DyeColor, DeferredBlock<T>> blocks) {
        Map<DyeColor, DeferredItem<BlockItem>> result = new LinkedHashMap<>();
        blocks.forEach((color, block) -> result.put(color, simpleBlockItem(block)));
        return Collections.unmodifiableMap(result);
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private ModItems() {
    }
}
