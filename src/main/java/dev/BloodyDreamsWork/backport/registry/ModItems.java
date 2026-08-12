package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.CushionItem;
import dev.BloodyDreamsWork.backport.content.ExplorerMapItem;
import dev.BloodyDreamsWork.backport.content.ExplorerMapType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import dev.BloodyDreamsWork.backport.content.PoplarBoatItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Backport.MODID);

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

    public static final RegistryObject<SignItem> POPLAR_SIGN = registerItem("poplar_sign",
            props -> new SignItem(ModBlocks.POPLAR_SIGN.get(), ModBlocks.POPLAR_WALL_SIGN.get(), props),
            () -> new Item.Properties().stacksTo(16));

    public static final RegistryObject<HangingSignItem> POPLAR_HANGING_SIGN = registerItem("poplar_hanging_sign",
            props -> new HangingSignItem(ModBlocks.POPLAR_HANGING_SIGN.get(), ModBlocks.POPLAR_WALL_HANGING_SIGN.get(), props),
            () -> new Item.Properties().stacksTo(16));

    public static final RegistryObject<BlockItem> SHELF_MUSHROOM = simpleBlockItem(ModBlocks.SHELF_MUSHROOM);
    public static final RegistryObject<BlockItem> RED_SHRUB = simpleBlockItem(ModBlocks.RED_SHRUB);

    public static final RegistryObject<PoplarBoatItem> POPLAR_BOAT = registerItem("poplar_boat",
            props -> new PoplarBoatItem(false, props), () -> new Item.Properties().stacksTo(1));

    public static final RegistryObject<PoplarBoatItem> POPLAR_CHEST_BOAT = registerItem("poplar_chest_boat",
            props -> new PoplarBoatItem(true, props), () -> new Item.Properties().stacksTo(1));

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
            result.put(type, registerItem(type.itemName(),
                    props -> new ExplorerMapItem(type, props), () -> new Item.Properties().stacksTo(1)));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, RegistryObject<CushionItem>> cushions() {
        Map<DyeColor, RegistryObject<CushionItem>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, registerItem(color.getName() + "_cushion",
                    props -> new CushionItem(color, props), Item.Properties::new));
        }
        return Collections.unmodifiableMap(result);
    }

    private static <T extends Block> Map<DyeColor, RegistryObject<BlockItem>> blockItems(
            Map<DyeColor, RegistryObject<T>> blocks) {
        Map<DyeColor, RegistryObject<BlockItem>> result = new LinkedHashMap<>();
        blocks.forEach((color, block) -> result.put(color, simpleBlockItem(block)));
        return Collections.unmodifiableMap(result);
    }

    private static RegistryObject<BlockItem> simpleBlockItem(RegistryObject<? extends Block> block) {
        return registerItem(block.getId().getPath(),
                properties -> new BlockItem(block.get(), properties), Item.Properties::new);
    }

    private static <T extends Item> RegistryObject<T> registerItem(
            String name,
            Function<Item.Properties, T> factory,
            Supplier<Item.Properties> properties) {
        ResourceKey<Item> key = ResourceKey.create(
                Registries.ITEM, Identifier.fromNamespaceAndPath(Backport.MODID, name));
        return ITEMS.register(name, () -> factory.apply(properties.get().setId(key)));
    }

    public static void register(BusGroup modBusGroup) {
        ITEMS.register(modBusGroup);
    }

    private ModItems() {
    }
}
