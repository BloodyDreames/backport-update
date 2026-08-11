package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerRespawnMixin {

    @Inject(method = "setRespawnPosition", at = @At("HEAD"), cancellable = true)
    private void backport$keepRespawnPoint(ResourceKey<Level> dimension, @Nullable BlockPos position,
                                           float angle, boolean forced, boolean sendMessage,
                                           CallbackInfo callback) {
        ServerPlayer player = (ServerPlayer) (Object) this;
        if (position != null
                && player.level().getBlockState(position).getBlock() instanceof StrawBedBlock) {
            callback.cancel();
        }
    }
}
