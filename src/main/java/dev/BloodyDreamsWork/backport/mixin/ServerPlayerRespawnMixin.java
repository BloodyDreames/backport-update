package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.server.level.ServerPlayer;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerRespawnMixin {

    @Inject(method = "setRespawnPosition(Lnet/minecraft/server/level/ServerPlayer$RespawnConfig;Z)V",
            at = @At("HEAD"), cancellable = true)
    private void backport$keepRespawnPoint(ServerPlayer.@Nullable RespawnConfig config,
                                           boolean showMessage, CallbackInfo callback) {
        if (config == null) {
            return;
        }

        ServerPlayer player = (ServerPlayer) (Object) this;
        var respawn = config.respawnData();
        if (respawn.dimension().equals(player.level().dimension())
                && player.level().getBlockState(respawn.pos()).getBlock() instanceof StrawBedBlock) {
            callback.cancel();
        }
    }
}
