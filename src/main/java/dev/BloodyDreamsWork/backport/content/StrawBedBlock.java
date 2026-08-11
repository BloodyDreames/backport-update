package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModSounds;
import dev.BloodyDreamsWork.backport.registry.ModStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.Nullable;

public class StrawBedBlock extends BedBlock {

    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);

    public StrawBedBlock(BlockBehaviour.Properties properties) {
        super(DyeColor.YELLOW, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.CONSUME;
        }

        if (state.getValue(PART) != BedPart.HEAD) {
            pos = pos.relative(state.getValue(FACING));
            state = level.getBlockState(pos);
            if (!state.is(this)) {
                return InteractionResult.CONSUME;
            }
        }

        if (!canSetSpawn(level)) {
            player.displayClientMessage(Component.translatable("block.backport.straw_bed.not_here"), true);
            return InteractionResult.SUCCESS;
        }

        if (state.getValue(OCCUPIED)) {
            player.displayClientMessage(Component.translatable("block.minecraft.bed.occupied"), true);
            return InteractionResult.SUCCESS;
        }

        player.startSleepInBed(pos)
                .ifLeft(problem -> {
                    if (problem.getMessage() != null) {
                        player.displayClientMessage(problem.getMessage(), true);
                    }
                })
                .ifRight(success -> player.awardStat(ModStats.SLEEP_IN_STRAW_BED.get()));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        crumble(level, pos, state);
    }

    private static void crumble(Level level, BlockPos pos, BlockState state) {
        BlockPos other = pos.relative(getConnectedDirection(state));
        level.removeBlock(pos, false);
        if (level.getBlockState(other).getBlock() instanceof StrawBedBlock) {
            level.removeBlock(other, false);
        }
        level.playSound(null, pos, ModSounds.STRAW_BED_BREAK_LEAVE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}
