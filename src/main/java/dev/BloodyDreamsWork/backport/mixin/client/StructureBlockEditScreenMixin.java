package dev.BloodyDreamsWork.backport.mixin.client;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.client.gui.screens.inventory.StructureBlockEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureBlockEditScreen.class)
public class StructureBlockEditScreenMixin {

    @Inject(method = "isPauseScreen", at = @At("HEAD"), cancellable = true)
    private void backport$pauseGame(CallbackInfoReturnable<Boolean> callback) {
        if (BackportConfig.vanillaBugfixes()) {
            callback.setReturnValue(true);
        }
    }
}
