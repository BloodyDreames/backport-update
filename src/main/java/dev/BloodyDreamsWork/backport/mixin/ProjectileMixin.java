package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.BlockProtection;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Projectile.class)
public class ProjectileMixin {

    @Inject(method = "mayInteract", at = @At("RETURN"), cancellable = true)
    private void backport$respectGameMode(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        if (!callback.getReturnValueZ() || !BackportConfig.vanillaBugfixes()) {
            return;
        }
        Projectile projectile = (Projectile) (Object) this;
        if (!BlockProtection.mayModify(level, projectile.getOwner(), pos)) {
            callback.setReturnValue(false);
        }
    }
}
