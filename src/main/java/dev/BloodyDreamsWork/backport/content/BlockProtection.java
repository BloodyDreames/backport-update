package dev.BloodyDreamsWork.backport.content;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

public final class BlockProtection {

    public static boolean mayModify(Level level, @Nullable Entity entity, BlockPos pos) {
        if (!(entity instanceof Player player)) {
            return true;
        }
        return player.mayBuild() && level.mayInteract(player, pos);
    }

    private BlockProtection() {
    }
}
