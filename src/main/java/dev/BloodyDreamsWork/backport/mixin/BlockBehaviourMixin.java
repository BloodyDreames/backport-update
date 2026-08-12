package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {
    @Inject(method = "<init>", at = @At("HEAD"))
    private static void backport$assignRegistrationKey(BlockBehaviour.Properties properties, CallbackInfo callback) {
        ResourceKey<Block> key = ModRegister.currentBlockKey();
        if (key != null) {
            properties.setId(key);
        }
    }
}
