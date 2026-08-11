package dev.BloodyDreamsWork.backport.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PoplarTrunkPlacer extends TrunkPlacer {
    public static final Codec<PoplarTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> trunkPlacerParts(instance)
                    .and(instance.group(
                            IntProvider.codec(0, 8).fieldOf("trunk_height_above_branches")
                                    .forGetter(placer -> placer.trunkHeightAboveBranches),
                            IntProvider.codec(1, 4).fieldOf("branch_amount")
                                    .forGetter(placer -> placer.branchAmount)))
                    .apply(instance, PoplarTrunkPlacer::new));

    private final IntProvider trunkHeightAboveBranches;
    private final IntProvider branchAmount;

    public PoplarTrunkPlacer(int baseHeight, int heightRandA, int heightRandB,
                             IntProvider trunkHeightAboveBranches, IntProvider branchAmount) {
        super(baseHeight, heightRandA, heightRandB);
        this.trunkHeightAboveBranches = trunkHeightAboveBranches;
        this.branchAmount = branchAmount;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModWorldgenTypes.POPLAR_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level,
                                                            BiConsumer<BlockPos, BlockState> blockSetter,
                                                            RandomSource random, int freeTreeHeight,
                                                            BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);

        int foliageAttachmentHeight = freeTreeHeight - this.trunkHeightAboveBranches.sample(random);

        for (int i = 0; i < freeTreeHeight; i++) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);

            List<Direction> branchDirections = getShuffledBranchDirections(random);
            if (i == foliageAttachmentHeight - 1) {
                int branches = this.branchAmount.sample(random);
                for (int branch = 0; branch < branches; branch++) {
                    Direction direction = branchDirections.get(branch);
                    this.placeLog(level, blockSetter, random,
                            pos.above(i).relative(direction), config,
                            getSidewaysStateModifier(direction));
                }
            }
        }

        return List.of(new FoliagePlacer.FoliageAttachment(
                pos.above(foliageAttachmentHeight), 0, false));
    }

    private static Function<BlockState, BlockState> getSidewaysStateModifier(Direction direction) {
        return state -> state.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
    }

    private static List<Direction> getShuffledBranchDirections(RandomSource random) {
        return Direction.allShuffled(random).stream()
                .filter(direction -> !direction.getAxis().isVertical())
                .toList();
    }
}
