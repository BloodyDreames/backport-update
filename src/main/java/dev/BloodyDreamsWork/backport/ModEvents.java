package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.world.level.block.Block;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

@Mod.EventBusSubscriber(modid = Backport.MODID)
public final class ModEvents {

    public static void addSignBlockEntities() {
        allow(BlockEntityType.SIGN,
                ModBlocks.POPLAR_SIGN.get(),
                ModBlocks.POPLAR_WALL_SIGN.get());
        allow(BlockEntityType.HANGING_SIGN,
                ModBlocks.POPLAR_HANGING_SIGN.get(),
                ModBlocks.POPLAR_WALL_HANGING_SIGN.get());
    }

    private static void allow(BlockEntityType<?> type, Block... blocks) {
        try {
            Field field = BlockEntityType.class.getDeclaredField("validBlocks");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Block> current = (Set<Block>) field.get(type);
            Set<Block> extended = new HashSet<>(current);
            extended.addAll(Arrays.asList(blocks));
            field.set(type, extended);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Failed to extend the block list of " + type, e);
        }
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
        if (level.isClientSide) {
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
