package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.content.ShieldPriority;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public class ShovelPriorityMixin {

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void backport$yieldToShield(UseOnContext context, CallbackInfoReturnable<InteractionResult> callback) {
        if (ShieldPriority.yieldsToShield(context)) {
            callback.setReturnValue(InteractionResult.PASS);
        }
    }
}
