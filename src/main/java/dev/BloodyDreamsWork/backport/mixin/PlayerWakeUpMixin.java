package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Player.class)
public class PlayerWakeUpMixin {

    @Inject(method = "stopSleepInBed", at = @At("HEAD"))
    private void backport$crumbleStrawBed(boolean wakeImmediately, boolean updateLevelForSleepingPlayers,
                                          CallbackInfo callback) {
        Player player = (Player) (Object) this;
        Level level = player.level();
        if (level.isClientSide) {
            return;
        }

        Optional<BlockPos> sleeping = player.getSleepingPos();
        if (sleeping.isEmpty()) {
            return;
        }

        BlockPos pos = sleeping.get();
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof StrawBedBlock) {
            level.scheduleTick(pos, state.getBlock(), 1);
        }
    }
}
