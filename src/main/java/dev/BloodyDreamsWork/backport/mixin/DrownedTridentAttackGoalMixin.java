package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.entity.monster.zombie.Drowned$DrownedTridentAttackGoal")
public class DrownedTridentAttackGoalMixin {

    private static final double MELEE_RANGE = 3.0;

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    private void backport$meleeWhenClose(CallbackInfoReturnable<Boolean> callback) {
        if (!callback.getReturnValueZ() || !BackportConfig.vanillaBugfixes()) {
            return;
        }

        Mob mob = ((RangedAttackGoalAccessor) this).backport$mob();
        LivingEntity target = mob.getTarget();
        if (target != null && mob.distanceToSqr(target) <= MELEE_RANGE * MELEE_RANGE) {
            callback.setReturnValue(false);
        }
    }
}
