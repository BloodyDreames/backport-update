package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.FriendlyFire;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.fish.Pufferfish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pufferfish.class)
public class PufferfishMixin {

    @Inject(method = "touch", at = @At("HEAD"), cancellable = true)
    private void backport$sparePartners(ServerLevel level, Mob target, CallbackInfo callback) {
        Pufferfish pufferfish = (Pufferfish) (Object) this;
        if (BackportConfig.vanillaBugfixes() && FriendlyFire.isProtected(pufferfish, target)) {
            callback.cancel();
        }
    }
}
