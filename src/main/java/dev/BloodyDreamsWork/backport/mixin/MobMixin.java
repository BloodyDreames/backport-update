package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mob.class)
public class MobMixin {

    @Redirect(
            method = "checkDespawn",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/world/entity/Mob;noActionTime:I",
                    opcode = Opcodes.PUTFIELD,
                    ordinal = 1))
    private void backport$idleWithoutPlayers(Mob mob, int value) {
        if (!BackportConfig.vanillaBugfixes() || playerNearby(mob)) {
            mob.setNoActionTime(value);
        }
    }

    private static boolean playerNearby(Mob mob) {
        Entity player = mob.level.getNearestPlayer(mob, -1.0);
        if (player == null) {
            return false;
        }
        int distance = mob.getType().getCategory().getNoDespawnDistance();
        return player.distanceToSqr(mob) < (double) distance * distance;
    }
}
