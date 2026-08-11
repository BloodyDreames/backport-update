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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Backport.MODID);

    private static RegistryObject<BlockItem> simpleBlockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(),
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final RegistryObject<BlockItem> POPLAR_LOG = simpleBlockItem(ModBlocks.POPLAR_LOG);
    public static final RegistryObject<BlockItem> POPLAR_WOOD = simpleBlockItem(ModBlocks.POPLAR_WOOD);
    public static final RegistryObject<BlockItem> STRIPPED_POPLAR_LOG = simpleBlockItem(ModBlocks.STRIPPED_POPLAR_LOG);
    public static final RegistryObject<BlockItem> STRIPPED_POPLAR_WOOD = simpleBlockItem(ModBlocks.STRIPPED_POPLAR_WOOD);
    public static final RegistryObject<BlockItem> POPLAR_PLANKS = simpleBlockItem(ModBlocks.POPLAR_PLANKS);
    public static final RegistryObject<BlockItem> POPLAR_STAIRS = simpleBlockItem(ModBlocks.POPLAR_STAIRS);
    public static final RegistryObject<BlockItem> POPLAR_SLAB = simpleBlockItem(ModBlocks.POPLAR_SLAB);
    public static final RegistryObject<BlockItem> POPLAR_FENCE = simpleBlockItem(ModBlocks.POPLAR_FENCE);
    public static final RegistryObject<BlockItem> POPLAR_FENCE_GATE = simpleBlockItem(ModBlocks.POPLAR_FENCE_GATE);
    public static final RegistryObject<BlockItem> POPLAR_PRESSURE_PLATE = simpleBlockItem(ModBlocks.POPLAR_PRESSURE_PLATE);
    public static final RegistryObject<BlockItem> POPLAR_BUTTON = simpleBlockItem(ModBlocks.POPLAR_BUTTON);
    public static final RegistryObject<BlockItem> POPLAR_TRAPDOOR = simpleBlockItem(ModBlocks.POPLAR_TRAPDOOR);
    public static final RegistryObject<BlockItem> POPLAR_DOOR = simpleBlockItem(ModBlocks.POPLAR_DOOR);

    public static final RegistryObject<BlockItem> RED_POPLAR_LEAVES = simpleBlockItem(ModBlocks.RED_POPLAR_LEAVES);
    public static final RegistryObject<BlockItem> ORANGE_POPLAR_LEAVES = simpleBlockItem(ModBlocks.ORANGE_POPLAR_LEAVES);
    public static final RegistryObject<BlockItem> YELLOW_POPLAR_LEAVES = simpleBlockItem(ModBlocks.YELLOW_POPLAR_LEAVES);

    public static final RegistryObject<BlockItem> POPLAR_SAPLING = simpleBlockItem(ModBlocks.POPLAR_SAPLING);

    public static final RegistryObject<SignItem> POPLAR_SIGN = ITEMS.register("poplar_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16),
                    ModBlocks.POPLAR_SIGN.get(), ModBlocks.POPLAR_WALL_SIGN.get()));

    public static final RegistryObject<HangingSignItem> POPLAR_HANGING_SIGN = ITEMS.register("poplar_hanging_sign",
            () -> new HangingSignItem(ModBlocks.POPLAR_HANGING_SIGN.get(), ModBlocks.POPLAR_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static final RegistryObject<BlockItem> SHELF_MUSHROOM = simpleBlockItem(ModBlocks.SHELF_MUSHROOM);
    public static final RegistryObject<BlockItem> RED_SHRUB = simpleBlockItem(ModBlocks.RED_SHRUB);

    public static final RegistryObject<PoplarBoatItem> POPLAR_BOAT = ITEMS.register("poplar_boat",
            () -> new PoplarBoatItem(false, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<PoplarBoatItem> POPLAR_CHEST_BOAT = ITEMS.register("poplar_chest_boat",
            () -> new PoplarBoatItem(true, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<BlockItem> STRAW_BED = simpleBlockItem(ModBlocks.STRAW_BED);

    public static final Map<DyeColor, RegistryObject<CushionItem>> CUSHIONS = cushions();

    public static final Map<ExplorerMapType, RegistryObject<ExplorerMapItem>> EXPLORER_MAPS = explorerMaps();

    public static final Map<DyeColor, RegistryObject<BlockItem>> WOOL_STAIRS =
            blockItems(ModBlocks.WOOL_STAIRS);
    public static final Map<DyeColor, RegistryObject<BlockItem>> WOOL_SLABS =
            blockItems(ModBlocks.WOOL_SLABS);
    public static final Map<DyeColor, RegistryObject<BlockItem>> CONCRETE_STAIRS =
            blockItems(ModBlocks.CONCRETE_STAIRS);
    public static final Map<DyeColor, RegistryObject<BlockItem>> CONCRETE_SLABS =
            blockItems(ModBlocks.CONCRETE_SLABS);

    private static Map<ExplorerMapType, RegistryObject<ExplorerMapItem>> explorerMaps() {
        Map<ExplorerMapType, RegistryObject<ExplorerMapItem>> result = new LinkedHashMap<>();
        for (ExplorerMapType type : ExplorerMapType.values()) {
            result.put(type, ITEMS.register(type.itemName(),
                    () -> new ExplorerMapItem(type, new Item.Properties().stacksTo(1))));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, RegistryObject<CushionItem>> cushions() {
        Map<DyeColor, RegistryObject<CushionItem>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, ITEMS.register(color.getName() + "_cushion",
                    () -> new CushionItem(color, new Item.Properties())));
        }
        return Collections.unmodifiableMap(result);
    }

    private static <T extends Block> Map<DyeColor, RegistryObject<BlockItem>> blockItems(
            Map<DyeColor, RegistryObject<T>> blocks) {
        Map<DyeColor, RegistryObject<BlockItem>> result = new LinkedHashMap<>();
        blocks.forEach((color, block) -> result.put(color, simpleBlockItem(block)));
        return Collections.unmodifiableMap(result);
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private ModItems() {
    }
}
