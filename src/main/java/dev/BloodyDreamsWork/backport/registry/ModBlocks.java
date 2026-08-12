package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.PoplarLeavesBlock;
import dev.BloodyDreamsWork.backport.content.PoplarSaplingBlock;
import dev.BloodyDreamsWork.backport.content.RedShrubBlock;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
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
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Backport.MODID);

    public static final DeferredBlock<RotatedPillarBlock> POPLAR_LOG = BLOCKS.registerBlock("poplar_log",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_POPLAR_LOG = BLOCKS.registerBlock("stripped_poplar_log",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<RotatedPillarBlock> POPLAR_WOOD = BLOCKS.registerBlock("poplar_wood",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_POPLAR_WOOD = BLOCKS.registerBlock("stripped_poplar_wood",
            RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<Block> POPLAR_PLANKS = BLOCKS.registerBlock("poplar_planks",
            Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<StairBlock> POPLAR_STAIRS = BLOCKS.registerBlock("poplar_stairs",
            props -> new StairBlock(POPLAR_PLANKS.get().defaultBlockState(), props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<SlabBlock> POPLAR_SLAB = BLOCKS.registerBlock("poplar_slab",
            SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<FenceBlock> POPLAR_FENCE = BLOCKS.registerBlock("poplar_fence",
            FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<FenceGateBlock> POPLAR_FENCE_GATE = BLOCKS.registerBlock("poplar_fence_gate",
            props -> new FenceGateBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<DoorBlock> POPLAR_DOOR = BLOCKS.registerBlock("poplar_door",
            props -> new DoorBlock(ModWoodTypes.POPLAR_SET, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<TrapDoorBlock> POPLAR_TRAPDOOR = BLOCKS.registerBlock("poplar_trapdoor",
            props -> new TrapDoorBlock(ModWoodTypes.POPLAR_SET, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<PressurePlateBlock> POPLAR_PRESSURE_PLATE = BLOCKS.registerBlock("poplar_pressure_plate",
            props -> new PressurePlateBlock(ModWoodTypes.POPLAR_SET, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
                    .mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<ButtonBlock> POPLAR_BUTTON = BLOCKS.registerBlock("poplar_button",
            props -> new ButtonBlock(ModWoodTypes.POPLAR_SET, 30, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));

    public static final DeferredBlock<PoplarLeavesBlock> RED_POPLAR_LEAVES = BLOCKS.registerBlock("red_poplar_leaves",
            props -> new PoplarLeavesBlock(ModParticles.RED_POPLAR_LEAVES, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.COLOR_RED));

    public static final DeferredBlock<PoplarLeavesBlock> ORANGE_POPLAR_LEAVES = BLOCKS.registerBlock("orange_poplar_leaves",
            props -> new PoplarLeavesBlock(ModParticles.ORANGE_POPLAR_LEAVES, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.COLOR_ORANGE));

    public static final DeferredBlock<PoplarLeavesBlock> YELLOW_POPLAR_LEAVES = BLOCKS.registerBlock("yellow_poplar_leaves",
            props -> new PoplarLeavesBlock(ModParticles.YELLOW_POPLAR_LEAVES, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.COLOR_YELLOW));

    public static final DeferredBlock<PoplarSaplingBlock> POPLAR_SAPLING = BLOCKS.registerBlock("poplar_sapling",
            PoplarSaplingBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredBlock<FlowerPotBlock> POTTED_POPLAR_SAPLING = BLOCKS.registerBlock("potted_poplar_sapling",
            props -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, POPLAR_SAPLING, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));

    public static final DeferredBlock<ShelfMushroomBlock> SHELF_MUSHROOM = BLOCKS.registerBlock("shelf_mushroom",
            ShelfMushroomBlock::new, () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.2F)
                    .noOcclusion()
                    .sound(ModSounds.shelfMushroom())
                    .pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<RedShrubBlock> RED_SHRUB = BLOCKS.registerBlock("red_shrub",
            RedShrubBlock::new, () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .noCollision()
                    .instabreak()
                    .sound(ModSounds.redShrub())
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<StandingSignBlock> POPLAR_SIGN = BLOCKS.registerBlock("poplar_sign",
            props -> new StandingSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));

    public static final DeferredBlock<WallSignBlock> POPLAR_WALL_SIGN = BLOCKS.registerBlock("poplar_wall_sign",
            props -> new WallSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
                    .overrideLootTable(POPLAR_SIGN.get().getLootTable()));

    public static final DeferredBlock<CeilingHangingSignBlock> POPLAR_HANGING_SIGN = BLOCKS.registerBlock("poplar_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN));

    public static final DeferredBlock<WallHangingSignBlock> POPLAR_WALL_HANGING_SIGN = BLOCKS.registerBlock("poplar_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodTypes.POPLAR, props),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)
                    .overrideLootTable(POPLAR_HANGING_SIGN.get().getLootTable()));

    public static final DeferredBlock<StrawBedBlock> STRAW_BED = BLOCKS.registerBlock("straw_bed",
            StrawBedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BED)
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(ModSounds.strawBed()));

    public static final Map<DyeColor, DeferredBlock<StairBlock>> WOOL_STAIRS =
            stairsFor("wool", "_wool_stairs");
    public static final Map<DyeColor, DeferredBlock<SlabBlock>> WOOL_SLABS =
            slabsFor("wool", "_wool_slab");
    public static final Map<DyeColor, DeferredBlock<StairBlock>> CONCRETE_STAIRS =
            stairsFor("concrete", "_concrete_stairs");
    public static final Map<DyeColor, DeferredBlock<SlabBlock>> CONCRETE_SLABS =
            slabsFor("concrete", "_concrete_slab");

    private static Block vanilla(DyeColor color, String suffix) {
        return BuiltInRegistries.BLOCK.getValue(
                Identifier.withDefaultNamespace(color.getName() + "_" + suffix));
    }

    private static Map<DyeColor, DeferredBlock<StairBlock>> stairsFor(String base, String nameSuffix) {
        Map<DyeColor, DeferredBlock<StairBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, BLOCKS.registerBlock(color.getName() + nameSuffix,
                    props -> new StairBlock(vanilla(color, base).defaultBlockState(), props),
                    () -> BlockBehaviour.Properties.ofFullCopy(vanilla(color, base))));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, DeferredBlock<SlabBlock>> slabsFor(String base, String nameSuffix) {
        Map<DyeColor, DeferredBlock<SlabBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, BLOCKS.registerBlock(color.getName() + nameSuffix,
                    SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(vanilla(color, base))));
        }
        return Collections.unmodifiableMap(result);
    }

    public static void register(IEventBus modEventBus) {
        ModWoodTypes.init();
        BLOCKS.register(modEventBus);
    }

    public static void registerFlowerPots() {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(POPLAR_SAPLING.getId(), POTTED_POPLAR_SAPLING);
    }

    public static void registerFlammability() {
        FireBlock fire = (FireBlock) Blocks.FIRE;

        fire.setFlammable(POPLAR_LOG.get(), 5, 5);
        fire.setFlammable(STRIPPED_POPLAR_LOG.get(), 5, 5);
        fire.setFlammable(POPLAR_WOOD.get(), 5, 5);
        fire.setFlammable(STRIPPED_POPLAR_WOOD.get(), 5, 5);

        fire.setFlammable(POPLAR_PLANKS.get(), 5, 20);
        fire.setFlammable(POPLAR_STAIRS.get(), 5, 20);
        fire.setFlammable(POPLAR_SLAB.get(), 5, 20);
        fire.setFlammable(POPLAR_FENCE.get(), 5, 20);
        fire.setFlammable(POPLAR_FENCE_GATE.get(), 5, 20);

        fire.setFlammable(RED_POPLAR_LEAVES.get(), 30, 60);
        fire.setFlammable(ORANGE_POPLAR_LEAVES.get(), 30, 60);
        fire.setFlammable(YELLOW_POPLAR_LEAVES.get(), 30, 60);

        for (DyeColor color : DyeColor.values()) {
            fire.setFlammable(WOOL_STAIRS.get(color).get(), 30, 60);
            fire.setFlammable(WOOL_SLABS.get(color).get(), 30, 60);
        }
    }

    private ModBlocks() {
    }
}
