package dev.BloodyDreamsWork.backport.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.function.Function;

public class PoplarFoliagePlacer extends FoliagePlacer {
    public static final Codec<PoplarFoliagePlacer> CODEC = RecordCodecBuilder.create(
            instance -> foliagePlacerParts(instance)
                    .and(instance.group(
                            IntProvider.codec(5, 16).fieldOf("height")
                                    .forGetter(placer -> placer.height),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("side_hole_chance")
                                    .forGetter(placer -> placer.sideHoleChance)))
                    .apply(instance, PoplarFoliagePlacer::new));

    private final IntProvider height;
    private final float sideHoleChance;

    public PoplarFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height, float sideHoleChance) {
        super(radius, offset);
        this.height = height;
        this.sideHoleChance = sideHoleChance;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModWorldgenTypes.POPLAR_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random,
                                 TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment,
                                 int foliageHeight, int foliageRadius, int offset) {
        boolean large = attachment.doubleTrunk();
        BlockPos center = attachment.pos().above(offset);
        int range = foliageRadius + attachment.radiusOffset() - 1;
        boolean alternateCorners = random.nextBoolean();

        placeLeavesRow(level, blockSetter, random, config, center,
                range - 2, foliageHeight - 1, large, foliageHeight, alternateCorners);
        placeLeavesRow(level, blockSetter, random, config, center,
                range - 1, foliageHeight - 2, large, foliageHeight, alternateCorners);
        placeLeavesRow(level, blockSetter, random, config, center,
                range - 1, foliageHeight - 3, large, foliageHeight, alternateCorners);

        for (int y = foliageHeight - 4; y >= 1; y--) {
            placeLeavesRow(level, blockSetter, random, config, center,
                    range, y, large, foliageHeight, alternateCorners);
        }

        replaceLeavesWithLog(level, blockSetter, config, random, center,
                range, foliageHeight - 4, large, foliageHeight, alternateCorners);
        placeLeavesRow(level, blockSetter, random, config, center,
                range - 1, 0, large, foliageHeight, alternateCorners);
        placeLeavesRow(level, blockSetter, random, config, center,
                Mth.clamp(range - 2, 1, 2), -1, large, foliageHeight, alternateCorners);
    }

    private void placeLeavesRow(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random,
                                TreeConfiguration config, BlockPos center, int range, int y, boolean large,
                                int foliageHeight, boolean alternateCorners) {
        int extra = large ? 1 : 0;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int x = -range; x <= range + extra; x++) {
            for (int z = -range; z <= range + extra; z++) {
                if (!shouldSkipLocation(random, x, y, z, range, large,
                        foliageHeight, alternateCorners)) {
                    mutable.setWithOffset(center, x, y, z);
                    tryPlaceLeaf(level, blockSetter, random, config, mutable);
                }
            }
        }
    }

    private void replaceLeavesWithLog(LevelSimulatedReader level, FoliageSetter blockSetter,
                                      TreeConfiguration config, RandomSource random, BlockPos center,
                                      int range, int y, boolean large, int foliageHeight,
                                      boolean alternateCorners) {
        int extra = large ? 1 : 0;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int x = -range; x <= range + extra; x++) {
            for (int z = -range; z <= range + extra; z++) {
                int absZ = Math.abs(z);
                int absX = Math.abs(x);
                int cornersToCut = getCornerBlocksToCutForRhombusShape(
                        x, z, range,
                        shouldRowBePartialRhombusShape(foliageHeight, y),
                        alternateCorners);

                boolean insideInnerRhombus = isWithinRhombusShape(
                        range, absX, absZ, cornersToCut, 2);
                boolean onInnerAxis = (absZ == 0 && range - absX >= 4)
                        || (absX == 0 && range - absZ >= 4);
                if (insideInnerRhombus && onInnerAxis) {
                    mutable.setWithOffset(center, x, y, z);
                    Direction.Axis axis = absZ == 0 ? Direction.Axis.X : Direction.Axis.Z;
                    Direction direction = Direction.fromAxisAndDirection(
                            axis, Direction.AxisDirection.POSITIVE);
                    tryPlaceLog(level, blockSetter, random, config, mutable,
                            getSidewaysStateModifier(direction));
                }
            }
        }
    }

    private static void tryPlaceLog(LevelSimulatedReader level, FoliageSetter blockSetter,
                                    RandomSource random, TreeConfiguration config, BlockPos pos,
                                    Function<BlockState, BlockState> stateModifier) {
        if (level.isStateAtPosition(pos,
                state -> state.equals(config.foliageProvider.getState(random, pos)))) {
            blockSetter.set(pos, stateModifier.apply(config.trunkProvider.getState(random, pos)));
        }
    }

    private static Function<BlockState, BlockState> getSidewaysStateModifier(Direction direction) {
        return state -> state.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height.sample(random);
    }

    private boolean shouldSkipLocation(RandomSource random, int x, int y, int z, int range,
                                       boolean large, int foliageHeight, boolean alternateCorners) {
        boolean partialRow = shouldRowBePartialRhombusShape(foliageHeight, y);
        int cornersToCut = getCornerBlocksToCutForRhombusShape(
                x, z, range, partialRow, alternateCorners);
        int absX = Math.abs(x);
        int absZ = Math.abs(z);
        boolean onSide = absX == range || absZ == range;
        if (partialRow && onSide) {
            return true;
        }

        int sideHole = random.nextFloat() <= this.sideHoleChance ? 1 : 0;
        return !isWithinRhombusShape(range, absX, absZ, cornersToCut, sideHole);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ,
                                         int range, boolean large) {
        throw new IllegalStateException("Overridden method needs more context");
    }

    @Override
    protected boolean shouldSkipLocationSigned(RandomSource random, int localX, int localY,
                                               int localZ, int range, boolean large) {
        throw new IllegalStateException("Overridden method needs more context");
    }

    private int getCornerBlocksToCutForRhombusShape(int x, int z, int range,
                                                    boolean partialRow, boolean alternateCorners) {
        boolean cutThisDiagonal = alternateCorners
                ? isLeftTopCornerOrRightLowerCorner(x, z)
                : isLeftLowerCornerOrRightTopCorner(x, z);
        if (cutThisDiagonal) {
            return range - 1;
        }
        return partialRow ? range + 1 : range;
    }

    private static boolean isWithinRhombusShape(int range, int absX, int absZ,
                                                int cornersToCut, int extraCut) {
        return absX + absZ <= range * 2 - cornersToCut - extraCut;
    }

    private static boolean isLeftLowerCornerOrRightTopCorner(int x, int z) {
        return x > 0 && z < 0 || z > 0 && x < 0;
    }

    private static boolean isLeftTopCornerOrRightLowerCorner(int x, int z) {
        return x > 0 && z > 0 || z < 0 && x < 0;
    }

    private boolean shouldRowBePartialRhombusShape(int foliageHeight, int y) {
        return foliageHeight - 1 == y || foliageHeight - 2 == y;
    }
}
