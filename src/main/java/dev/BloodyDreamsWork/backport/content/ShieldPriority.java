package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.context.UseOnContext;

import org.jetbrains.annotations.Nullable;

public final class ShieldPriority {

    public static boolean yieldsToShield(UseOnContext context) {
        if (!BackportConfig.vanillaBugfixes()) {
            return false;
        }

        @Nullable Player player = context.getPlayer();
        if (player == null) {
            return false;
        }

        InteractionHand otherHand = context.getHand() == InteractionHand.MAIN_HAND
                ? InteractionHand.OFF_HAND
                : InteractionHand.MAIN_HAND;
        return player.getItemInHand(otherHand).getItem() instanceof ShieldItem;
    }

    private ShieldPriority() {
    }
}
