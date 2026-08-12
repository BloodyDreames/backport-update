package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.armadillo.ArmadilloAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmadilloAi.ArmadilloBallUp.class)
public class ArmadilloBallUpMixin {

    @Inject(method = "checkExtraStartConditions"
            + "(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/animal/armadillo/Armadillo;)Z",
            at = @At("RETURN"), cancellable = true)
    private void backport$notInLiquid(ServerLevel level, Armadillo armadillo,
                                      CallbackInfoReturnable<Boolean> callback) {
        if (callback.getReturnValueZ() && BackportConfig.vanillaBugfixes() && armadillo.isInLiquid()) {
            callback.setReturnValue(false);
        }
    }
}
