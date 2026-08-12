package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.mixin.FireBlockInvoker;
import dev.BloodyDreamsWork.backport.content.PoplarLeavesBlock;
import dev.BloodyDreamsWork.backport.content.PoplarSaplingBlock;
import dev.BloodyDreamsWork.backport.content.RedShrubBlock;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.Material;
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
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_POPLAR_LOG = BLOCKS.register("stripped_poplar_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<RotatedPillarBlock> POPLAR_WOOD = BLOCKS.register("poplar_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_POPLAR_WOOD = BLOCKS.register("stripped_poplar_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<Block> POPLAR_PLANKS = BLOCKS.register("poplar_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<StairBlock> POPLAR_STAIRS = BLOCKS.register("poplar_stairs",
            () -> new StairBlock(POPLAR_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
                            .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<SlabBlock> POPLAR_SLAB = BLOCKS.register("poplar_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)
                    .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<FenceBlock> POPLAR_FENCE = BLOCKS.register("poplar_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
                    .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<FenceGateBlock> POPLAR_FENCE_GATE = BLOCKS.register("poplar_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
                            .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<DoorBlock> POPLAR_DOOR = BLOCKS.register("poplar_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                            .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<TrapDoorBlock> POPLAR_TRAPDOOR = BLOCKS.register("poplar_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)
                            .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<PressurePlateBlock> POPLAR_PRESSURE_PLATE = BLOCKS.register("poplar_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
                            .color(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<WoodButtonBlock> POPLAR_BUTTON = BLOCKS.register("poplar_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));

    public static final RegistryObject<PoplarLeavesBlock> RED_POPLAR_LEAVES = BLOCKS.register("red_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.RED_POPLAR_LEAVES,
                    BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                            .color(MaterialColor.COLOR_RED)));

    public static final RegistryObject<PoplarLeavesBlock> ORANGE_POPLAR_LEAVES = BLOCKS.register("orange_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.ORANGE_POPLAR_LEAVES,
                    BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                            .color(MaterialColor.COLOR_ORANGE)));

    public static final RegistryObject<PoplarLeavesBlock> YELLOW_POPLAR_LEAVES = BLOCKS.register("yellow_poplar_leaves",
            () -> new PoplarLeavesBlock(ModParticles.YELLOW_POPLAR_LEAVES,
                    BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                            .color(MaterialColor.COLOR_YELLOW)));

    public static final RegistryObject<PoplarSaplingBlock> POPLAR_SAPLING = BLOCKS.register("poplar_sapling",
            () -> new PoplarSaplingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<FlowerPotBlock> POTTED_POPLAR_SAPLING = BLOCKS.register("potted_poplar_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, POPLAR_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

    public static final RegistryObject<ShelfMushroomBlock> SHELF_MUSHROOM = BLOCKS.register("shelf_mushroom",
            () -> new ShelfMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN)
                    .strength(0.2F)
                    .noOcclusion()
                    .sound(ModSounds.shelfMushroom())));

    public static final RegistryObject<RedShrubBlock> RED_SHRUB = BLOCKS.register("red_shrub",
            () -> new RedShrubBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_RED)
                    .noCollission()
                    .instabreak()
                    .sound(ModSounds.redShrub())
                    .offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final RegistryObject<StandingSignBlock> POPLAR_SIGN = BLOCKS.register("poplar_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),
                            ModWoodTypes.POPLAR));

    public static final RegistryObject<WallSignBlock> POPLAR_WALL_SIGN = BLOCKS.register("poplar_wall_sign",
            () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)
                            .lootFrom(POPLAR_SIGN),
                    ModWoodTypes.POPLAR));

    public static final RegistryObject<StandingSignBlock> POPLAR_HANGING_SIGN = BLOCKS.register("poplar_hanging_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),
                            ModWoodTypes.POPLAR));

    public static final RegistryObject<WallSignBlock> POPLAR_WALL_HANGING_SIGN = BLOCKS.register("poplar_wall_hanging_sign",
            () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)
                            .lootFrom(POPLAR_HANGING_SIGN),
                    ModWoodTypes.POPLAR));

    public static final RegistryObject<StrawBedBlock> STRAW_BED = BLOCKS.register("straw_bed",
            () -> new StrawBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_BED)
                    .color(MaterialColor.COLOR_YELLOW)
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
        return Registry.BLOCK.get(
                new ResourceLocation(color.getName() + "_" + suffix));
    }

    private static Map<DyeColor, RegistryObject<StairBlock>> stairsFor(String base, String nameSuffix) {
        Map<DyeColor, RegistryObject<StairBlock>> result = new LinkedHashMap<>();
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

    private static Map<DyeColor, RegistryObject<SlabBlock>> slabsFor(String base, String nameSuffix) {
        Map<DyeColor, RegistryObject<SlabBlock>> result = new LinkedHashMap<>();
        for (DyeColor color : DyeColor.values()) {
            result.put(color, BLOCKS.register(color.getName() + nameSuffix,
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(vanilla(color, base)))));
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
