package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class SpectatorPortalMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void backport$enterPortalAsSpectator(CallbackInfo callback) {
        Player player = (Player) (Object) this;
        if (!player.isSpectator() || !BackportConfig.vanillaBugfixes()) {
            return;
        }

        Level level = player.level();
        BlockPos pos = player.blockPosition();
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof Portal) {
            state.entityInside(level, pos, player);
        }
    }
}
