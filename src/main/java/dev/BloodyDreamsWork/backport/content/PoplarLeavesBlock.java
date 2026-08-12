package dev.BloodyDreamsWork.backport.content;

import com.mojang.serialization.MapCodec;
import dev.BloodyDreamsWork.backport.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class PoplarLeavesBlock extends LeavesBlock {
    private static final float FALLING_LEAVES_CHANCE = 0.1F;

    private static final int AMBIENT_CHANCE = 400;

    private final Supplier<SimpleParticleType> fallingParticle;

    public PoplarLeavesBlock(Supplier<SimpleParticleType> fallingParticle, BlockBehaviour.Properties properties) {
        super(FALLING_LEAVES_CHANCE, properties);
        this.fallingParticle = fallingParticle;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        playAmbient(level, pos, random);
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(level, pos, random, this.fallingParticle.get());
    }

    @Override
    public MapCodec<PoplarLeavesBlock> codec() {
        return MapCodec.unit(() -> this);
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
