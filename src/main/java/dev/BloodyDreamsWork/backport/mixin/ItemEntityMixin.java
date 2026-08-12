package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    protected ItemEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Redirect(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;applyGravity()V")
    )
    private void backport$gravityOnlyWhenMoving(ItemEntity self) {
        if (!BackportConfig.vanillaBugfixes() || backport$movesThisTick()) {
            this.applyGravity();
        }
    }

    @Unique
    private boolean backport$movesThisTick() {
        return !this.onGround()
                || this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5F
                || (this.tickCount + this.getId()) % 4 == 0;
    }
}
