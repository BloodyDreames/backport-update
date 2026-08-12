package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.BlockProtection;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TurtleEggBlock.class)
public class TurtleEggBlockMixin {

    @Inject(method = "destroyEgg", at = @At("HEAD"), cancellable = true)
    private void backport$checkPermission(Level level, BlockState state, BlockPos pos, Entity entity,
                                          int chance, CallbackInfo callback) {
        if (BackportConfig.vanillaBugfixes() && !BlockProtection.mayModify(level, entity, pos)) {
            callback.cancel();
        }
    }
}
