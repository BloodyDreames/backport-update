package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import org.jetbrains.annotations.Nullable;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {

    @Redirect(
            method = "onHitEntity",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;deflect"
                            + "(Lnet/minecraft/world/entity/projectile/ProjectileDeflection;"
                            + "Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Z)Z"))
    private boolean backport$dontBounceOffInvulnerable(AbstractArrow arrow, ProjectileDeflection deflection,
                                                       @Nullable Entity hit, @Nullable Entity owner,
                                                       boolean fromAttack) {
        if (BackportConfig.vanillaBugfixes()) {
            return false;
        }
        return ((Projectile) arrow).deflect(deflection, hit, owner, fromAttack);
    }
}
