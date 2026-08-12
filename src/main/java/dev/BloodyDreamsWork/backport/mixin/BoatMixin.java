package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractBoat.class)
public class BoatMixin {

    @Redirect(
            method = "clampRotation",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;setYHeadRot(F)V"))
    private void backport$keepGaze(Entity passenger, float bodyRotation) {
        if (!BackportConfig.vanillaBugfixes() || passenger instanceof Player) {
            passenger.setYHeadRot(bodyRotation);
        }
    }

    @Redirect(
            method = "positionRider",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;setYHeadRot(F)V",
                    ordinal = 1))
    private void backport$dontTurnAnimalHeads(Entity passenger, float rotation) {
        if (!BackportConfig.vanillaBugfixes() || !(passenger instanceof Animal)) {
            passenger.setYHeadRot(rotation);
        }
    }
}
