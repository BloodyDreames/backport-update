package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.BlockProtection;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Mixin(FarmBlock.class)
public class FarmBlockMixin {

    @Redirect(
            method = "fallOn",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraftforge/common/ForgeHooks;onFarmlandTrample"
                            + "(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;"
                            + "Lnet/minecraft/world/level/block/state/BlockState;F"
                            + "Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean backport$checkPermission(Level level, BlockPos pos, BlockState dirt,
                                             float fallDistance, Entity entity) {
        return ForgeHooks.onFarmlandTrample(level, pos, dirt, fallDistance, entity)
                && (!BackportConfig.vanillaBugfixes()
                || BlockProtection.mayModify(level, entity, pos));
    }
}
