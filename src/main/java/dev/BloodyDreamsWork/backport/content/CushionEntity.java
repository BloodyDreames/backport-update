package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModItems;
import dev.BloodyDreamsWork.backport.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.Nullable;

public class CushionEntity extends Entity {

    public static final float HEIGHT = 0.25F;

    private static final EntityDataAccessor<Integer> DATA_COLOR =
            SynchedEntityData.defineId(CushionEntity.class, EntityDataSerializers.INT);

    private static final int SUPPORT_CHECK_INTERVAL = 20;

    public CushionEntity(EntityType<? extends CushionEntity> type, Level level) {
        super(type, level);
    }

    public DyeColor getColor() {
        return DyeColor.byId(this.entityData.get(DATA_COLOR));
    }

    public void setColor(DyeColor color) {
        this.entityData.set(DATA_COLOR, color.getId());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_COLOR, DyeColor.WHITE.getId());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setColor(DyeColor.byId(tag.getInt("color")));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("color", this.getColor().getId());
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide
                && this.tickCount % SUPPORT_CHECK_INTERVAL == 0
                && !this.hasSupport()) {
            this.breakCushion(null);
        }
    }

    private boolean hasSupport() {
        BlockPos below = BlockPos.containing(this.getX(), this.getY() - 0.05, this.getZ());
        VoxelShape shape = this.level().getBlockState(below).getCollisionShape(this.level(), below);
        if (shape.isEmpty()) {
            return false;
        }
        return below.getY() + shape.max(Direction.Axis.Y) >= this.getY() - 0.06;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive() || this.isVehicle()) {
            return InteractionResult.PASS;
        }

        if (!this.level().isClientSide && player.startRiding(this)) {
            this.playSound(ModSounds.CUSHION_SIT.get(), 1.0F, 1.0F);
            this.gameEvent(GameEvent.ENTITY_INTERACT, player);
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.level().isClientSide) {
            this.playSound(ModSounds.CUSHION_GET_UP.get(), 1.0F, 1.0F);
            this.gameEvent(GameEvent.ENTITY_INTERACT, passenger);
        }
    }

    @Override
    public boolean skipAttackInteraction(Entity attacker) {
        if (!(attacker instanceof Player)) {
            return false;
        }
        if (!this.level().isClientSide && !this.isRemoved() && !this.isInvulnerable()) {
            this.breakCushion(attacker);
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isRemoved() || this.isInvulnerableTo(source)) {
            return false;
        }
        if (!this.level().isClientSide) {
            this.breakCushion(source.getEntity());
        }
        return true;
    }

    @Override
    public ItemStack getPickResult() {
        ItemStack stack = new ItemStack(ModItems.CUSHIONS.get(this.getColor()).get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    private void breakCushion(@Nullable Entity source) {
        this.playSound(ModSounds.CUSHION_BREAK.get(), 1.0F, 1.0F);
        this.gameEvent(GameEvent.ENTITY_DIE, source);
        if (!(source instanceof Player player && player.getAbilities().instabuild)) {
            this.spawnAtLocation(this.getPickResult());
        }
        this.discard();
    }
}
