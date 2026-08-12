package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.PoplarLeavesBlock;
import dev.BloodyDreamsWork.backport.content.PoplarSaplingBlock;
import dev.BloodyDreamsWork.backport.content.RedShrubBlock;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Backport.MODID);

    public static final RegistryObject<RotatedPillarBlock> POPLAR_LOG = registerBlock("poplar_log",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_POPLAR_LOG = registerBlock("stripped_poplar_log",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<RotatedPillarBlock> POPLAR_WOOD = registerBlock("poplar_wood",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_POPLAR_WOOD = registerBlock("stripped_poplar_wood",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<Block> POPLAR_PLANKS = registerBlock("poplar_planks",
            Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<StairBlock> POPLAR_STAIRS = registerBlock("poplar_stairs",
            props -> new StairBlock(POPLAR_PLANKS.get().defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<SlabBlock> POPLAR_SLAB = registerBlock("poplar_slab",
            SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<FenceBlock> POPLAR_FENCE = registerBlock("poplar_fence",
            FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<FenceGateBlock> POPLAR_FENCE_GATE = registerBlock("poplar_fence_gate",
            props -> new FenceGateBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<DoorBlock> POPLAR_DOOR = registerBlock("poplar_door",
            props -> new DoorBlock(ModWoodTypes.POPLAR_SET, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<TrapDoorBlock> POPLAR_TRAPDOOR = registerBlock("poplar_trapdoor",
            props -> new TrapDoorBlock(ModWoodTypes.POPLAR_SET, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<PressurePlateBlock> POPLAR_PRESSURE_PLATE = registerBlock("poplar_pressure_plate",
            props -> new PressurePlateBlock(ModWoodTypes.POPLAR_SET, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final RegistryObject<ButtonBlock> POPLAR_BUTTON = registerBlock("poplar_button",
            props -> new ButtonBlock(ModWoodTypes.POPLAR_SET, 30, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));

    public static final RegistryObject<PoplarLeavesBlock> RED_POPLAR_LEAVES = registerBlock("red_poplar_leaves",
            props -> new PoplarLeavesBlock(ModParticles.RED_POPLAR_LEAVES, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.COLOR_RED));

    public static final RegistryObject<PoplarLeavesBlock> ORANGE_POPLAR_LEAVES = registerBlock("orange_poplar_leaves",
            props -> new PoplarLeavesBlock(ModParticles.ORANGE_POPLAR_LEAVES, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.COLOR_ORANGE));

    public static final RegistryObject<PoplarLeavesBlock> YELLOW_POPLAR_LEAVES = registerBlock("yellow_poplar_leaves",
            props -> new PoplarLeavesBlock(ModParticles.YELLOW_POPLAR_LEAVES, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.COLOR_YELLOW));

    public static final RegistryObject<PoplarSaplingBlock> POPLAR_SAPLING = registerBlock("poplar_sapling",
            PoplarSaplingBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final RegistryObject<FlowerPotBlock> POTTED_POPLAR_SAPLING = registerBlock("potted_poplar_sapling",
            props -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, POPLAR_SAPLING, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));

    public static final RegistryObject<ShelfMushroomBlock> SHELF_MUSHROOM = registerBlock("shelf_mushroom",
            ShelfMushroomBlock::new, () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.2F)
                    .noOcclusion()
                    .sound(ModSounds.shelfMushroom())
                    .pushReaction(PushReaction.DESTROY));

    public static final RegistryObject<RedShrubBlock> RED_SHRUB = registerBlock("red_shrub",
            RedShrubBlock::new, () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .noCollision()
                    .instabreak()
                    .sound(ModSounds.redShrub())
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY));

    public static final RegistryObject<StandingSignBlock> POPLAR_SIGN = registerBlock("poplar_sign",
            props -> new StandingSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));

    public static final RegistryObject<WallSignBlock> POPLAR_WALL_SIGN = registerBlock("poplar_wall_sign",
            props -> new WallSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
                    .overrideLootTable(POPLAR_SIGN.get().getLootTable()));

    public static final RegistryObject<CeilingHangingSignBlock> POPLAR_HANGING_SIGN = registerBlock("poplar_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN));

    public static final RegistryObject<WallHangingSignBlock> POPLAR_WALL_HANGING_SIGN = registerBlock("poplar_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)
                    .overrideLootTable(POPLAR_HANGING_SIGN.get().getLootTable()));

    public static final RegistryObject<StrawBedBlock> STRAW_BED = registerBlock("straw_bed",
            StrawBedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(vanilla(DyeColor.WHITE, "bed"))
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(ModSounds.strawBed()));

    public static final Map<DyeColor, RegistryObject<StairBlock>> WOOL_STAIRS =
            stairsFor("wool", "_wool_stairs");
    public static final Map<DyeColor, RegistryObject<SlabBlock>> WOOL_SLABS =
            slabsFor("wool", "_wool_slab");
    public static final Map<DyeColor, RegistryObject<StairBlock>> CONCRETE_STAIRS =
            stairsFor("concrete", "_concrete_stairs");
    public static final Map<DyeColor, RegistryObject<SlabBlock>> CONCRETE_SLABS =
            slabsFor("concrete", "_concrete_slab");

    private static Block vanilla(DyeColor color, String suffix) {
        return BuiltInRegistries.BLOCK.getValue(
                Identifier.withDefaultNamespace(color.getName() + "_" + suffix));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(
            String name,
            Function<BlockBehaviour.Properties, T> factory,
            Supplier<BlockBehaviour.Properties> properties) {
        ResourceKey<Block> key = ResourceKey.create(
                Registries.BLOCK, Identifier.fromNamespaceAndPath(Backport.MODID, name));
        return BLOCKS.register(name, () -> factory.apply(properties.get().setId(key)));
    }

    private static Map<DyeColor, RegistryObject<StairBlock>> stairsFor(String base, String nameSuffix) {
        Map<DyeColor, RegistryObject<StairBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, registerBlock(color.getName() + nameSuffix,
                    props -> base.equals("wool")
                            ? new FlammableWoolStairBlock(vanilla(color, base).defaultBlockState(), props)
                            : new StairBlock(vanilla(color, base).defaultBlockState(), props),
                    () -> BlockBehaviour.Properties.ofFullCopy(vanilla(color, base))));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, RegistryObject<SlabBlock>> slabsFor(String base, String nameSuffix) {
        Map<DyeColor, RegistryObject<SlabBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, registerBlock(color.getName() + nameSuffix,
                    props -> base.equals("wool") ? new FlammableWoolSlabBlock(props) : new SlabBlock(props),
                    () -> BlockBehaviour.Properties.ofFullCopy(vanilla(color, base))));
        }
        return Collections.unmodifiableMap(result);
    }

    public static void register(BusGroup modBusGroup) {
        ModWoodTypes.init();
        BLOCKS.register(modBusGroup);
    }

    public static void registerFlowerPots() {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(POPLAR_SAPLING.getId(), POTTED_POPLAR_SAPLING);
    }

    public static void registerFlammability() {

    }

    private static final class FlammableWoolStairBlock extends StairBlock {
        private FlammableWoolStairBlock(BlockState base, BlockBehaviour.Properties properties) {
            super(base, properties);
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    }

    private static final class FlammableWoolSlabBlock extends SlabBlock {
        private FlammableWoolSlabBlock(BlockBehaviour.Properties properties) {
            super(properties);
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    }

    private ModBlocks() {
    }
}
