package dev.BloodyDreamsWork.backport.content;

import com.mojang.serialization.MapCodec;
import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.server.level.ServerLevel;

import java.util.Map;

public class ShelfMushroomBlock extends HorizontalDirectionalBlock
        implements BonemealableBlock, SimpleWaterloggedBlock {

    public static final MapCodec<ShelfMushroomBlock> CODEC = simpleCodec(ShelfMushroomBlock::new);

    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final Map<Direction, VoxelShape> SMALL_SHAPES = Map.of(
            Direction.NORTH, Block.box(3.0, 8.0, 9.0, 13.0, 11.0, 16.0),
            Direction.SOUTH, Block.box(3.0, 8.0, 0.0, 13.0, 11.0, 7.0),
            Direction.WEST, Block.box(9.0, 8.0, 3.0, 16.0, 11.0, 13.0),
            Direction.EAST, Block.box(0.0, 8.0, 3.0, 7.0, 11.0, 13.0));

    private static final Map<Direction, VoxelShape> LARGE_SHAPES = Map.of(
            Direction.NORTH, Block.box(1.0, 6.0, 6.0, 15.0, 11.0, 16.0),
            Direction.SOUTH, Block.box(1.0, 6.0, 0.0, 15.0, 11.0, 10.0),
            Direction.WEST, Block.box(6.0, 6.0, 1.0, 16.0, 11.0, 15.0),
            Direction.EAST, Block.box(0.0, 6.0, 1.0, 10.0, 11.0, 15.0));

    public ShelfMushroomBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(AGE, 0)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE, WATERLOGGED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Map<Direction, VoxelShape> shapes = state.getValue(AGE) == 0 ? SMALL_SHAPES : LARGE_SHAPES;
        return shapes.get(state.getValue(FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clicked = context.getClickedFace();
        if (!clicked.getAxis().isHorizontal()) {
            return null;
        }
        BlockState state = this.defaultBlockState()
                .setValue(FACING, clicked)
                .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
        return state.canSurvive(context.getLevel(), context.getClickedPos()) ? state : null;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockPos supportPos = pos.relative(facing.getOpposite());
        return level.getBlockState(supportPos).isFaceSturdy(level, supportPos, facing);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess,
                                     BlockPos pos, Direction direction, BlockPos neighborPos,
                                     BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (direction == state.getValue(FACING).getOpposite() && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return state;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        super.fallOn(level, state, pos, entity, fallDistance * 0.5);
        playBounceSound(entity);
    }

    private void playBounceSound(Entity entity) {
        if (entity.isSuppressingBounce() || !BackportConfig.SHELF_MUSHROOM_BOUNCE.get()) {
            return;
        }

        Vec3 movement = entity.getDeltaMovement();
        if (movement.y >= -entity.getGravity()) {
            return;
        }

        if (entity instanceof LivingEntity
                && entity.level() instanceof Level entityLevel && !entityLevel.isClientSide()) {
            entityLevel.playSound(null, entity.blockPosition(),
                    ModSounds.SHELF_MUSHROOM_BOUNCE.get(), SoundSource.BLOCKS,
                    0.5F, 0.9F + entityLevel.getRandom().nextFloat() * 0.2F);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) == 0;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, 1), Block.UPDATE_ALL);
    }
}
