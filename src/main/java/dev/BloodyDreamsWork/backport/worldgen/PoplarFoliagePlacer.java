package dev.BloodyDreamsWork.backport.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

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
        boolean stretchAlongX = random.nextBoolean();

        for (int y = offset; y >= offset - foliageHeight; y--) {
            int distanceFromMiddle = Math.abs(y - (offset - foliageHeight / 2));
            int radius = Math.max(foliageRadius + attachment.radiusOffset() - distanceFromMiddle, 0);
            placeRhombusRow(level, blockSetter, random, config, attachment, radius, y, stretchAlongX);
        }
    }

    private void placeRhombusRow(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random,
                                 TreeConfiguration config, FoliageAttachment attachment,
                                 int radius, int y, boolean stretchAlongX) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int stretchedX = stretchAlongX ? x : x * 2;
                int stretchedZ = stretchAlongX ? z * 2 : z;
                if (Math.abs(stretchedX) + Math.abs(stretchedZ) > radius) {
                    continue;
                }
                if (shouldSkipLocation(random, Math.abs(x), y, Math.abs(z), radius, attachment.doubleTrunk())) {
                    continue;
                }
                tryPlaceLeaf(level, blockSetter, random, config, attachment.pos().offset(x, y, z));
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height.sample(random);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        boolean onEdge = localX == range || localZ == range;
        return onEdge && random.nextFloat() < this.sideHoleChance;
    }
}
