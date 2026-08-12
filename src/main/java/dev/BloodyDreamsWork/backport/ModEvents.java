package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.player.PlayerSetSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;

@EventBusSubscriber(modid = Backport.MODID)
public final class ModEvents {

    @SubscribeEvent
    public static void addSignBlockEntities(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityTypes.SIGN,
                ModBlocks.POPLAR_SIGN.get(),
                ModBlocks.POPLAR_WALL_SIGN.get());
        event.modify(BlockEntityTypes.HANGING_SIGN,
                ModBlocks.POPLAR_HANGING_SIGN.get(),
                ModBlocks.POPLAR_WALL_HANGING_SIGN.get());
    }

    @SubscribeEvent
    public static void keepRespawnPoint(PlayerSetSpawnEvent event) {
        BlockPos spawn = event.getNewSpawn();
        if (spawn != null
                && event.getEntity().level().getBlockState(spawn).getBlock() instanceof StrawBedBlock) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void crumbleStrawBed(PlayerWakeUpEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide()) {
            return;
        }

        player.getSleepingPos().ifPresent(pos -> {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof StrawBedBlock) {
                level.scheduleTick(pos, state.getBlock(), 1);
            }
        });
    }

    private ModEvents() {
    }
}
