package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.mixin.FireBlockInvoker;
import dev.BloodyDreamsWork.backport.mixin.FlowerPotBlockAccessor;
import dev.BloodyDreamsWork.backport.content.PoplarLeavesBlock;
import dev.BloodyDreamsWork.backport.content.PoplarSaplingBlock;
import dev.BloodyDreamsWork.backport.content.RedShrubBlock;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ModBlocks {
    public static final ModRegister<Block> BLOCKS = ModRegister.create(BuiltInRegistries.BLOCK);

    public static final ModRegister.Entry<RotatedPillarBlock> POPLAR_LOG = BLOCKS.register("poplar_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<RotatedPillarBlock> STRIPPED_POPLAR_LOG = BLOCKS.register("stripped_poplar_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<RotatedPillarBlock> POPLAR_WOOD = BLOCKS.register("poplar_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<RotatedPillarBlock> STRIPPED_POPLAR_WOOD = BLOCKS.register("stripped_poplar_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<Block> POPLAR_PLANKS = BLOCKS.register("poplar_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<StairBlock> POPLAR_STAIRS = BLOCKS.register("poplar_stairs",
            () -> new StairBlock(POPLAR_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
                            .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<SlabBlock> POPLAR_SLAB = BLOCKS.register("poplar_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<FenceBlock> POPLAR_FENCE = BLOCKS.register("poplar_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final ModRegister.Entry<FenceGateBlock> POPLAR_FENCE_GATE = BLOCKS.register("poplar_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
                            .mapColor(MapColor.TERRACOTTA_WHITE),
                            ModWoodTypes.POPLAR));

    public static final ModRegister.Entry<DoorBlock> POPLAR_DOOR = BLOCKS.register("poplar_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                            .mapColor(MapColor.TERRACOTTA_WHITE),
                            ModWoodTypes.POPLAR_SET));

    public static final ModRegister.Entry<TrapDoorBlock> POPLAR_TRAPDOOR = BLOCKS.register("poplar_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)
                            .mapColor(MapColor.TERRACOTTA_WHITE),
                            ModWoodTypes.POPLAR_SET));

    public static final ModRegister.Entry<PressurePlateBlock> POPLAR_PRESSURE_PLATE = BLOCKS.register("poplar_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
                            .mapColor(MapColor.TERRACOTTA_WHITE),
                            ModWoodTypes.POPLAR_SET));

    public static final ModRegister.Entry<ButtonBlock> POPLAR_BUTTON = BLOCKS.register("poplar_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
                            ModWoodTypes.POPLAR_SET, 30, true));

    public static final ModRegister.Entry<PoplarLeavesBlock> RED_POPLAR_LEAVES = BLOCKS.register("red_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.RED_POPLAR_LEAVES,
                    BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                            .mapColor(MapColor.COLOR_RED)));

    public static final ModRegister.Entry<PoplarLeavesBlock> ORANGE_POPLAR_LEAVES = BLOCKS.register("orange_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.ORANGE_POPLAR_LEAVES,
                    BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                            .mapColor(MapColor.COLOR_ORANGE)));

    public static final ModRegister.Entry<PoplarLeavesBlock> YELLOW_POPLAR_LEAVES = BLOCKS.register("yellow_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.YELLOW_POPLAR_LEAVES,
                    BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                            .mapColor(MapColor.COLOR_YELLOW)));

    public static final ModRegister.Entry<PoplarSaplingBlock> POPLAR_SAPLING = BLOCKS.register("poplar_sapling",
            () -> new PoplarSaplingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final ModRegister.Entry<FlowerPotBlock> POTTED_POPLAR_SAPLING = BLOCKS.register("potted_poplar_sapling",
            () -> new FlowerPotBlock(POPLAR_SAPLING.get(),
                    BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

    public static final ModRegister.Entry<ShelfMushroomBlock> SHELF_MUSHROOM = BLOCKS.register("shelf_mushroom",
            () -> new ShelfMushroomBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.2F)
                    .noOcclusion()
                    .sound(ModSounds.shelfMushroom())
                    .pushReaction(PushReaction.DESTROY)));

    public static final ModRegister.Entry<RedShrubBlock> RED_SHRUB = BLOCKS.register("red_shrub",
            () -> new RedShrubBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .noCollission()
                    .instabreak()
                    .sound(ModSounds.redShrub())
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final ModRegister.Entry<StandingSignBlock> POPLAR_SIGN = BLOCKS.register("poplar_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),
                            ModWoodTypes.POPLAR));

    public static final ModRegister.Entry<WallSignBlock> POPLAR_WALL_SIGN = BLOCKS.register("poplar_wall_sign",
            () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN),
                    ModWoodTypes.POPLAR));

    public static final ModRegister.Entry<CeilingHangingSignBlock> POPLAR_HANGING_SIGN = BLOCKS.register("poplar_hanging_sign",
            () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN),
                            ModWoodTypes.POPLAR));

    public static final ModRegister.Entry<WallHangingSignBlock> POPLAR_WALL_HANGING_SIGN = BLOCKS.register("poplar_wall_hanging_sign",
            () -> new WallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN),
                            ModWoodTypes.POPLAR));

    public static final ModRegister.Entry<StrawBedBlock> STRAW_BED = BLOCKS.register("straw_bed",
            () -> new StrawBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_BED)
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(ModSounds.strawBed())));

    public static final Map<DyeColor, ModRegister.Entry<StairBlock>> WOOL_STAIRS =
            stairsFor("wool", "_wool_stairs");
    public static final Map<DyeColor, ModRegister.Entry<SlabBlock>> WOOL_SLABS =
            slabsFor("wool", "_wool_slab");
    public static final Map<DyeColor, ModRegister.Entry<StairBlock>> CONCRETE_STAIRS =
            stairsFor("concrete", "_concrete_stairs");
    public static final Map<DyeColor, ModRegister.Entry<SlabBlock>> CONCRETE_SLABS =
            slabsFor("concrete", "_concrete_slab");

    private static Block vanilla(DyeColor color, String suffix) {
        return BuiltInRegistries.BLOCK.get(
                new ResourceLocation(color.getName() + "_" + suffix));
    }

    private static Map<DyeColor, ModRegister.Entry<StairBlock>> stairsFor(String base, String nameSuffix) {
        Map<DyeColor, ModRegister.Entry<StairBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, BLOCKS.register(color.getName() + nameSuffix,
                    () -> {
                        Block source = vanilla(color, base);
                        return new StairBlock(source.defaultBlockState(),
                                BlockBehaviour.Properties.copy(source));
                    }));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, ModRegister.Entry<SlabBlock>> slabsFor(String base, String nameSuffix) {
        Map<DyeColor, ModRegister.Entry<SlabBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, BLOCKS.register(color.getName() + nameSuffix,
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(vanilla(color, base)))));
        }
        return Collections.unmodifiableMap(result);
    }

    public static void register() {
        ModWoodTypes.init();
    }

    public static void registerFlowerPots() {
        FlowerPotBlockAccessor.backport$getPottedByContent()
                .put(POPLAR_SAPLING.get(), POTTED_POPLAR_SAPLING.get());
    }

    public static void registerFlammability() {
        FireBlockInvoker fire = (FireBlockInvoker) Blocks.FIRE;

        fire.backport$setFlammable(POPLAR_LOG.get(), 5, 5);
        fire.backport$setFlammable(STRIPPED_POPLAR_LOG.get(), 5, 5);
        fire.backport$setFlammable(POPLAR_WOOD.get(), 5, 5);
        fire.backport$setFlammable(STRIPPED_POPLAR_WOOD.get(), 5, 5);

        fire.backport$setFlammable(POPLAR_PLANKS.get(), 5, 20);
        fire.backport$setFlammable(POPLAR_STAIRS.get(), 5, 20);
        fire.backport$setFlammable(POPLAR_SLAB.get(), 5, 20);
        fire.backport$setFlammable(POPLAR_FENCE.get(), 5, 20);
        fire.backport$setFlammable(POPLAR_FENCE_GATE.get(), 5, 20);

        fire.backport$setFlammable(RED_POPLAR_LEAVES.get(), 30, 60);
        fire.backport$setFlammable(ORANGE_POPLAR_LEAVES.get(), 30, 60);
        fire.backport$setFlammable(YELLOW_POPLAR_LEAVES.get(), 30, 60);

        for (DyeColor color : DyeColor.values()) {
            fire.backport$setFlammable(WOOL_STAIRS.get(color).get(), 30, 60);
            fire.backport$setFlammable(WOOL_SLABS.get(color).get(), 30, 60);
        }
    }

    private ModBlocks() {
    }
}
