package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.content.VanillaRegistryContext;
import net.minecraft.data.BuiltinRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinRegistries.class)
public class VanillaRegistriesMixin {

    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void backport$enterVanillaBuild(CallbackInfo callback) {
        VanillaRegistryContext.enter();
    }

    @Inject(method = "bootstrap", at = @At("RETURN"))
    private static void backport$leaveVanillaBuild(CallbackInfo callback) {
        VanillaRegistryContext.leave();
    }
}
