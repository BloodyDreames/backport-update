package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.mixin.FireBlockInvoker;
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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Backport.MODID);

    public static final RegistryObject<RotatedPillarBlock> POPLAR_LOG = BLOCKS.register("poplar_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_POPLAR_LOG = BLOCKS.register("stripped_poplar_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<RotatedPillarBlock> POPLAR_WOOD = BLOCKS.register("poplar_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_POPLAR_WOOD = BLOCKS.register("stripped_poplar_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<Block> POPLAR_PLANKS = BLOCKS.register("poplar_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<StairBlock> POPLAR_STAIRS = BLOCKS.register("poplar_stairs",
            () -> new StairBlock(POPLAR_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
                            .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<SlabBlock> POPLAR_SLAB = BLOCKS.register("poplar_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<FenceBlock> POPLAR_FENCE = BLOCKS.register("poplar_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
                    .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<FenceGateBlock> POPLAR_FENCE_GATE = BLOCKS.register("poplar_fence_gate",
            () -> new FenceGateBlock(ModWoodTypes.POPLAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
                            .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<DoorBlock> POPLAR_DOOR = BLOCKS.register("poplar_door",
            () -> new DoorBlock(ModWoodTypes.POPLAR_SET,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
                            .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<TrapDoorBlock> POPLAR_TRAPDOOR = BLOCKS.register("poplar_trapdoor",
            () -> new TrapDoorBlock(ModWoodTypes.POPLAR_SET,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
                            .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<PressurePlateBlock> POPLAR_PRESSURE_PLATE = BLOCKS.register("poplar_pressure_plate",
            () -> new PressurePlateBlock(ModWoodTypes.POPLAR_SET,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
                            .mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<ButtonBlock> POPLAR_BUTTON = BLOCKS.register("poplar_button",
            () -> new ButtonBlock(ModWoodTypes.POPLAR_SET, 30,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));

    public static final RegistryObject<PoplarLeavesBlock> RED_POPLAR_LEAVES = BLOCKS.register("red_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.RED_POPLAR_LEAVES,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                            .mapColor(MapColor.COLOR_RED)));

    public static final RegistryObject<PoplarLeavesBlock> ORANGE_POPLAR_LEAVES = BLOCKS.register("orange_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.ORANGE_POPLAR_LEAVES,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                            .mapColor(MapColor.COLOR_ORANGE)));

    public static final RegistryObject<PoplarLeavesBlock> YELLOW_POPLAR_LEAVES = BLOCKS.register("yellow_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.YELLOW_POPLAR_LEAVES,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                            .mapColor(MapColor.COLOR_YELLOW)));

    public static final RegistryObject<PoplarSaplingBlock> POPLAR_SAPLING = BLOCKS.register("poplar_sapling",
            () -> new PoplarSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<FlowerPotBlock> POTTED_POPLAR_SAPLING = BLOCKS.register("potted_poplar_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, POPLAR_SAPLING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));

    public static final RegistryObject<ShelfMushroomBlock> SHELF_MUSHROOM = BLOCKS.register("shelf_mushroom",
            () -> new ShelfMushroomBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.2F)
                    .noOcclusion()
                    .sound(ModSounds.shelfMushroom())
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<RedShrubBlock> RED_SHRUB = BLOCKS.register("red_shrub",
            () -> new RedShrubBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .noCollission()
                    .instabreak()
                    .sound(ModSounds.redShrub())
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<StandingSignBlock> POPLAR_SIGN = BLOCKS.register("poplar_sign",
            () -> new StandingSignBlock(ModWoodTypes.POPLAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));

    public static final RegistryObject<WallSignBlock> POPLAR_WALL_SIGN = BLOCKS.register("poplar_wall_sign",
            () -> new WallSignBlock(ModWoodTypes.POPLAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
                            .lootFrom(POPLAR_SIGN)));

    public static final RegistryObject<CeilingHangingSignBlock> POPLAR_HANGING_SIGN = BLOCKS.register("poplar_hanging_sign",
            () -> new CeilingHangingSignBlock(ModWoodTypes.POPLAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));

    public static final RegistryObject<WallHangingSignBlock> POPLAR_WALL_HANGING_SIGN = BLOCKS.register("poplar_wall_hanging_sign",
            () -> new WallHangingSignBlock(ModWoodTypes.POPLAR,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)
                            .lootFrom(POPLAR_HANGING_SIGN)));

    public static final RegistryObject<StrawBedBlock> STRAW_BED = BLOCKS.register("straw_bed",
            () -> new StrawBedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BED)
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(ModSounds.strawBed())));

    public static final Map<DyeColor, RegistryObject<StairBlock>> WOOL_STAIRS =
            stairsFor("wool", "_wool_stairs");
    public static final Map<DyeColor, RegistryObject<SlabBlock>> WOOL_SLABS =
            slabsFor("wool", "_wool_slab");
    public static final Map<DyeColor, RegistryObject<StairBlock>> CONCRETE_STAIRS =
            stairsFor("concrete", "_concrete_stairs");
    public static final Map<DyeColor, RegistryObject<SlabBlock>> CONCRETE_SLABS =
            slabsFor("concrete", "_concrete_slab");

    private static Block vanilla(DyeColor color, String suffix) {
        return BuiltInRegistries.BLOCK.get(
                new ResourceLocation(color.getName() + "_" + suffix));
    }

    private static Map<DyeColor, RegistryObject<StairBlock>> stairsFor(String base, String nameSuffix) {
        Map<DyeColor, RegistryObject<StairBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, BLOCKS.register(color.getName() + nameSuffix,
                    () -> {
                        Block source = vanilla(color, base);
                        return new StairBlock(source.defaultBlockState(),
                                BlockBehaviour.Properties.ofFullCopy(source));
                    }));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<DyeColor, RegistryObject<SlabBlock>> slabsFor(String base, String nameSuffix) {
        Map<DyeColor, RegistryObject<SlabBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, BLOCKS.register(color.getName() + nameSuffix,
                    () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(vanilla(color, base)))));
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
