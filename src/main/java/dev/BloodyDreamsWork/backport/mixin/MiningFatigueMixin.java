package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.jspecify.annotations.Nullable;

@Mixin(Player.class)
public class MiningFatigueMixin {

    private static final int FIRST_BROKEN_AMPLIFIER = 2;

    private static final float CORRECTION = 10.0F;

    @Inject(method = "getDestroySpeed(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)F",
            at = @At("RETURN"), cancellable = true)
    private void backport$fixFatigueSteps(BlockState state, @Nullable BlockPos pos,
                                          CallbackInfoReturnable<Float> callback) {
        if (!BackportConfig.vanillaBugfixes()) {
            return;
        }

        Player player = (Player) (Object) this;
        MobEffectInstance fatigue = player.getEffect(MobEffects.MINING_FATIGUE);
        if (fatigue != null && fatigue.getAmplifier() >= FIRST_BROKEN_AMPLIFIER) {
            callback.setReturnValue(callback.getReturnValueF() * CORRECTION);
        }
    }
}
