package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModSounds;
import dev.BloodyDreamsWork.backport.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class CushionItem extends Item {

    private final DyeColor color;

    public CushionItem(DyeColor color, Properties properties) {
        super(properties);
        this.color = color;
    }

    public DyeColor getColor() {
        return this.color;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getClickedFace() != Direction.UP) {
            return InteractionResult.PASS;
        }

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        double height = surfaceHeight(level, pos, context.getPlayer(), context.getClickLocation());
        Vec3 spot = new Vec3(pos.getX() + 0.5, height, pos.getZ() + 0.5);

        if (!level.getEntitiesOfClass(CushionEntity.class,
                new AABB(spot, spot).inflate(0.4, 0.2, 0.4)).isEmpty()) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide()) {
            CushionEntity cushion = new CushionEntity(ModEntities.CUSHION.get(), level);
            cushion.setColor(this.color);
            cushion.setCustomName(context.getItemInHand().get(DataComponents.CUSTOM_NAME));
            cushion.setPos(spot);
            level.addFreshEntity(cushion);

            level.playSound(null, spot.x, spot.y, spot.z,
                    ModSounds.CUSHION_PLACE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, spot);

            context.getItemInHand().consume(1, context.getPlayer());
        }
        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }

    private static double surfaceHeight(Level level, BlockPos pos, @Nullable Player player, Vec3 clicked) {
        if (player == null || !level.getBlockState(pos).is(ModTags.Blocks.CUSHION_USES_COLLISION_SHAPE)) {
            return clicked.y;
        }

        Vec3 eye = player.getEyePosition();
        Vec3 end = eye.add(player.getViewVector(1.0F).scale(player.blockInteractionRange()));
        BlockHitResult hit = level.clip(new ClipContext(
                eye, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        boolean insideSameBlock = hit.getType() == HitResult.Type.BLOCK
                && hit.getBlockPos().equals(pos)
                && hit.getDirection() == Direction.UP;
        return insideSameBlock ? hit.getLocation().y : clicked.y;
    }
}
