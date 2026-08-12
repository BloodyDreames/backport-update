package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExplorationMapFunction.class)
public class ExplorationMapFunctionMixin {

    @Inject(method = "run", at = @At("RETURN"), cancellable = true)
    private void backport$dropUnfilledMap(ItemStack stack, LootContext context,
                                          CallbackInfoReturnable<ItemStack> callback) {
        if (BackportConfig.vanillaBugfixes() && callback.getReturnValue().is(Items.MAP)) {
            callback.setReturnValue(ItemStack.EMPTY);
        }
    }
}
