package dev.BloodyDreamsWork.backport.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.LinkedHashSet;
import java.util.Set;

public class FallenTreeFeature extends Feature<FallenTreeConfiguration> {

    private static final int MIN_LOG_LENGTH = 3;

    private static final int STUMP_GAP = 2;

    public FallenTreeFeature(Codec<FallenTreeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FallenTreeConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        FallenTreeConfiguration config = context.config();

        if (!canPlaceLog(level, origin)) {
            return false;
        }

        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos start = origin.relative(direction, STUMP_GAP);
        int length = config.logLength().sample(random);

        Set<BlockPos> logs = new LinkedHashSet<>();
        for (int i = 0; i < length; i++) {
            BlockPos pos = start.relative(direction, i);
            if (!canPlaceLog(level, pos)) {
                break;
            }
            logs.add(pos);
        }

        if (logs.size() < MIN_LOG_LENGTH) {
            return false;
        }

        placeLog(level, origin, config.trunkProvider().getState(level, random, origin), Direction.Axis.Y);
        for (BlockPos pos : logs) {
            placeLog(level, pos, config.trunkProvider().getState(level, random, pos), direction.getAxis());
        }

        decorate(level, random, config.stumpDecorators(), Set.of(origin));
        decorate(level, random, config.logDecorators(), logs);
        return true;
    }

    private static boolean canPlaceLog(WorldGenLevel level, BlockPos pos) {
        if (!level.getBlockState(pos).canBeReplaced()) {
            return false;
        }
        BlockState below = level.getBlockState(pos.below());
        return below.isFaceSturdy(level, pos.below(), Direction.UP);
    }

    private void placeLog(LevelAccessor level, BlockPos pos, BlockState log, Direction.Axis axis) {
        BlockState state = log.hasProperty(RotatedPillarBlock.AXIS)
                ? log.setValue(RotatedPillarBlock.AXIS, axis)
                : log;
        setBlock(level, pos, state);
    }

    private void decorate(WorldGenLevel level, RandomSource random,
                          Iterable<TreeDecorator> decorators, Set<BlockPos> logs) {
        TreeDecorator.Context context = new TreeDecorator.Context(level,
                (pos, state) -> setBlock(level, pos, state),
                random, logs, Set.of(), Set.of());
        decorators.forEach(decorator -> decorator.place(context));
    }
}
