package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.FriendlyFire;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderDragon.class)
public class EnderDragonMixin {

    @Redirect(
            method = "knockBack(Lnet/minecraft/server/level/ServerLevel;Ljava/util/List;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt"
                            + "(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean backport$spareOnKnockback(Entity target, DamageSource source, float amount) {
        return this.backport$hurtUnlessAllied(target, source, amount);
    }

    @Redirect(
            method = "hurt(Ljava/util/List;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt"
                            + "(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean backport$spareOnCharge(Entity target, DamageSource source, float amount) {
        return this.backport$hurtUnlessAllied(target, source, amount);
    }

    private boolean backport$hurtUnlessAllied(Entity target, DamageSource source, float amount) {
        EnderDragon dragon = (EnderDragon) (Object) this;
        if (BackportConfig.vanillaBugfixes() && FriendlyFire.isProtected(dragon, target)) {
            return false;
        }
        return target.hurt(source, amount);
    }
}
