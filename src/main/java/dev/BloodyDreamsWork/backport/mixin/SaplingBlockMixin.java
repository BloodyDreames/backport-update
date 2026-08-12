package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin {

    @Shadow
    @Final
    protected TreeGrower treeGrower;

    @Inject(method = "isValidBonemealTarget", at = @At("HEAD"), cancellable = true)
    private void backport$onlyWhenCanGrow(LevelReader level, BlockPos pos, BlockState state,
                                          CallbackInfoReturnable<Boolean> callback) {
        if (!BackportConfig.vanillaBugfixes() || this.backport$canGrowAlone()) {
            return;
        }
        if (!this.backport$hasSquare(level, pos, state)) {
            callback.setReturnValue(false);
        }
    }

    private boolean backport$canGrowAlone() {
        TreeGrowerAccessor grower = (TreeGrowerAccessor) (Object) this.treeGrower;
        return grower.backport$tree().isPresent()
                || grower.backport$secondaryTree().isPresent()
                || grower.backport$flowers().isPresent();
    }

    private boolean backport$hasSquare(LevelReader level, BlockPos pos, BlockState state) {
        for (int x = 0; x >= -1; x--) {
            for (int z = 0; z >= -1; z--) {
                if (level.getBlockState(pos.offset(x, 0, z)).is(state.getBlock())
                        && level.getBlockState(pos.offset(x + 1, 0, z)).is(state.getBlock())
                        && level.getBlockState(pos.offset(x, 0, z + 1)).is(state.getBlock())
                        && level.getBlockState(pos.offset(x + 1, 0, z + 1)).is(state.getBlock())) {
                    return true;
                }
            }
        }
        return false;
    }
}
