package dev.BloodyDreamsWork.backport.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

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

        for (int i = 0; i < freeTreeHeight; i++) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);
        }

        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();
        attachments.add(new FoliagePlacer.FoliageAttachment(pos.above(freeTreeHeight), 0, false));

        int branches = this.branchAmount.sample(random);
        int aboveBranches = this.trunkHeightAboveBranches.sample(random);
        int branchZoneTop = Math.max(freeTreeHeight - aboveBranches, 1);

        for (int i = 0; i < branches; i++) {
            int branchY = 1 + random.nextInt(branchZoneTop);
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos branchPos = pos.above(branchY).relative(direction);

            this.placeLog(level, blockSetter, random, branchPos, config);
            BlockPos branchTop = branchPos.above();
            this.placeLog(level, blockSetter, random, branchTop, config);

            attachments.add(new FoliagePlacer.FoliageAttachment(branchTop, 0, false));
        }

        return ImmutableList.copyOf(attachments);
    }
}
