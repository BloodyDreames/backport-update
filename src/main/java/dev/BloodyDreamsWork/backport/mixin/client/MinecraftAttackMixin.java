package dev.BloodyDreamsWork.backport.mixin.client;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.jetbrains.annotations.Nullable;

@Mixin(Minecraft.class)
public class MinecraftAttackMixin {

    @Shadow
    @Nullable
    public HitResult hitResult;

    @Shadow
    @Nullable
    public MultiPlayerGameMode gameMode;

    @Unique
    private boolean backport$attackedEntity;

    @Inject(method = "startAttack", at = @At("RETURN"))
    private void backport$rememberEntityHit(CallbackInfoReturnable<Boolean> callback) {
        if (this.hitResult != null && this.hitResult.getType() == HitResult.Type.ENTITY) {
            this.backport$attackedEntity = true;
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void backport$keepBlockIntact(boolean leftClick, CallbackInfo callback) {
        if (!leftClick) {
            this.backport$attackedEntity = false;
            return;
        }
        if (BackportConfig.vanillaBugfixes() && this.backport$attackedEntity && this.gameMode != null) {
            this.gameMode.stopDestroyBlock();
            callback.cancel();
        }
    }
}
