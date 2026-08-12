package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.content.VanillaRegistryContext;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.registries.VanillaRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaRegistries.class)
public class VanillaRegistriesMixin {

    @Inject(method = "createLookup", at = @At("HEAD"))
    private static void backport$enterVanillaBuild(CallbackInfoReturnable<HolderLookup.Provider> callback) {
        VanillaRegistryContext.enter();
    }

    @Inject(method = "createLookup", at = @At("RETURN"))
    private static void backport$leaveVanillaBuild(CallbackInfoReturnable<HolderLookup.Provider> callback) {
        VanillaRegistryContext.leave();
    }
}
