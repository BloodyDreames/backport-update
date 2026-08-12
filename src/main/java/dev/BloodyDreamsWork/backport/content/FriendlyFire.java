package dev.BloodyDreamsWork.backport.content;

import net.minecraft.world.scores.Team;
import net.minecraft.world.entity.Entity;

import org.jspecify.annotations.Nullable;

public final class FriendlyFire {

    public static boolean isProtected(@Nullable Entity source, @Nullable Entity target) {
        if (source == null || target == null || source == target) {
            return false;
        }
        Team team = source.getTeam();
        return team != null && team.isAlliedTo(target.getTeam()) && !team.isAllowFriendlyFire();
    }

    private FriendlyFire() {
    }
}
