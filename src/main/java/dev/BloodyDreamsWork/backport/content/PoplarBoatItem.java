package dev.BloodyDreamsWork.backport.content;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class PoplarBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);

    private final boolean hasChest;

    public PoplarBoatItem(boolean hasChest, Item.Properties properties) {
        super(properties);
        this.hasChest = hasChest;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        HitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hit.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(held);
        }

        Vec3 view = player.getViewVector(1.0F);
        List<Entity> nearby = level.getEntities(player,
                player.getBoundingBox().expandTowards(view.scale(5.0)).inflate(1.0), ENTITY_PREDICATE);
        if (!nearby.isEmpty()) {
            Vec3 eyes = player.getEyePosition();
            for (Entity entity : nearby) {
                AABB box = entity.getBoundingBox().inflate(entity.getPickRadius());
                if (box.contains(eyes)) {
                    return InteractionResultHolder.pass(held);
                }
            }
        }

        if (hit.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(held);
        }

        Boat boat = createBoat(level, hit, held, player);
        boat.setYRot(player.getYRot());
        if (!level.noCollision(boat, boat.getBoundingBox())) {
            return InteractionResultHolder.fail(held);
        }

        if (!level.isClientSide) {
            level.addFreshEntity(boat);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, hit.getLocation());
            held.consume(1, player);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(held, level.isClientSide());
    }

    private Boat createBoat(Level level, HitResult hit, ItemStack stack, Player player) {
        Vec3 at = hit.getLocation();
        Boat boat = this.hasChest
                ? new PoplarChestBoat(level, at.x, at.y, at.z)
                : new PoplarBoat(level, at.x, at.y, at.z);
        if (level instanceof ServerLevel serverLevel) {
            EntityType.<Boat>createDefaultStackConfig(serverLevel, stack, player).accept(boat);
        }
        return boat;
    }
}
