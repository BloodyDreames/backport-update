package dev.BloodyDreamsWork.backport.content;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedShrubBlock extends BushBlock implements BonemealableBlock {

    public static final MapCodec<RedShrubBlock> CODEC = simpleCodec(RedShrubBlock::new);

    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    private static final int SPREAD_ATTEMPTS = 4;

    public RedShrubBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !freeNeighbours(level, pos).isEmpty();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        List<BlockPos> candidates = freeNeighbours(level, pos);
        if (candidates.isEmpty()) {
            return;
        }
        Collections.shuffle(candidates, new java.util.Random(random.nextLong()));

        int planted = 0;
        for (BlockPos target : candidates) {
            if (planted >= SPREAD_ATTEMPTS) {
                break;
            }
            level.setBlock(target, this.defaultBlockState(), Block.UPDATE_ALL);
            planted++;
        }
    }

    private List<BlockPos> freeNeighbours(LevelReader level, BlockPos pos) {
        List<BlockPos> result = new ArrayList<>(4);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos target = pos.relative(direction);
            if (level.getBlockState(target).canBeReplaced()
                    && this.defaultBlockState().canSurvive(level, target)) {
                result.add(target);
            }
        }
        return result;
    }
}
