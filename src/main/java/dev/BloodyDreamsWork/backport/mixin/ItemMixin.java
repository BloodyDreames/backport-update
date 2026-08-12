package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "<init>", at = @At("HEAD"))
    private static void backport$assignRegistrationKey(Item.Properties properties, CallbackInfo callback) {
        ResourceKey<Item> key = ModRegister.currentItemKey();
        if (key != null) {
            properties.setId(key);
        }
    }
}
