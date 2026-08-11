package dev.BloodyDreamsWork.backport.mixin.client;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AdvancementsScreen.class)
public abstract class AdvancementsScreenMixin extends Screen {

    @Shadow
    @Final
    @Nullable
    private Screen lastScreen;

    private AdvancementsScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void backport$returnToPauseMenu(int keyCode, int scanCode, int modifiers,
                                            CallbackInfoReturnable<Boolean> callback) {
        if (!BackportConfig.vanillaBugfixes() || this.lastScreen == null || this.minecraft == null) {
            return;
        }
        if (this.minecraft.options.keyAdvancements.matches(keyCode, scanCode)) {
            this.minecraft.setScreen(this.lastScreen);
            callback.setReturnValue(true);
        }
    }
}
