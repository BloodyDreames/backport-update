package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class PoplarLeavesBlock extends LeavesBlock {
    private static final int AMBIENT_CHANCE = 400;

    private final Supplier<SimpleParticleType> fallingParticle;

    public PoplarLeavesBlock(Supplier<SimpleParticleType> fallingParticle, BlockBehaviour.Properties properties) {
        super(properties);
        this.fallingParticle = fallingParticle;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        if (random.nextInt(10) == 0) {
            BlockPos below = pos.below();
            BlockState belowState = level.getBlockState(below);
            if (!isFaceFull(belowState.getCollisionShape(level, below), Direction.UP)) {
                level.addParticle(this.fallingParticle.get(),
                        pos.getX() + random.nextDouble(), pos.getY() - 0.05,
                        pos.getZ() + random.nextDouble(), 0.0, -0.02, 0.0);
            }
        }

        playAmbient(level, pos, random);
    }

    private void playAmbient(Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(AMBIENT_CHANCE) != 0) {
            return;
        }
        level.playLocalSound(
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                ModSounds.POPLAR_LEAVES_AMBIENT.get(), SoundSource.BLOCKS,
                0.6F, 0.8F + random.nextFloat() * 0.4F, false);
    }
}
