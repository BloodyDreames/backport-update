package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.BlockProtection;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import org.jspecify.annotations.Nullable;

@Mixin(FarmlandBlock.class)
public class FarmBlockMixin {

    @Redirect(
            method = "fallOn",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/FarmlandBlock;turnToDirt"
                            + "(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;"
                            + "Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"))
    private void backport$checkPermission(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        if (!BackportConfig.vanillaBugfixes() || BlockProtection.mayModify(level, entity, pos)) {
            FarmlandBlock.turnToDirt(entity, state, level, pos);
        }
    }
}
