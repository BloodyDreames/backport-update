package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.worldgen.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.chunk.ChunkGenerator;

import java.util.Optional;

public class PoplarSaplingBlock extends SaplingBlock {

    public static final AbstractTreeGrower GROWER = new AbstractTreeGrower() {
        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random,
                                                                                 boolean hasFlowers) {
            return null;
        }

        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(
                ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state,
                RandomSource random, boolean hasFlowers) {
            return level.registryAccess().registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY)
                    .getHolder(ModConfiguredFeatures.RED_POPLAR).orElse(null);
        }
    };

    public PoplarSaplingBlock(BlockBehaviour.Properties properties) {
        super(GROWER, properties);
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
            return;
        }

        ResourceKey<ConfiguredFeature<?, ?>> variant =
                ModConfiguredFeatures.POPLAR_VARIANTS[random.nextInt(ModConfiguredFeatures.POPLAR_VARIANTS.length)];

        Holder<ConfiguredFeature<?, ?>> holder = level.registryAccess()
                .registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY)
                .getHolder(variant)
                .orElse(null);
        if (holder == null) {
            return;
        }

        BlockState replacement = level.getFluidState(pos).createLegacyBlock();
        level.setBlock(pos, replacement, 4);
        if (holder.value().place(level, level.getChunkSource().getGenerator(), random, pos)) {
            if (level.getBlockState(pos) == replacement) {
                level.sendBlockUpdated(pos, state, replacement, 2);
            }
        } else {
            level.setBlock(pos, state, 4);
        }
    }
}
