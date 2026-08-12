package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.FriendlyFire;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public class SlimeMixin {

    @Inject(method = "dealDamage", at = @At("HEAD"), cancellable = true)
    private void backport$sparePartners(LivingEntity target, CallbackInfo callback) {
        Slime slime = (Slime) (Object) this;
        if (BackportConfig.vanillaBugfixes() && FriendlyFire.isProtected(slime, target)) {
            callback.cancel();
        }
    }
}
