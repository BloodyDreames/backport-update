package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModSounds {
    public static final ModRegister<SoundEvent> SOUNDS =
            ModRegister.create(BuiltInRegistries.SOUND_EVENT);

    public static final ModRegister.Entry<SoundEvent> POPLAR_LEAVES_AMBIENT =
            register("block.poplar_leaves.ambient");

    public static final ModRegister.Entry<SoundEvent> SHELF_MUSHROOM_BOUNCE =
            register("block.shelf_mushroom.bounce");
    public static final ModRegister.Entry<SoundEvent> SHELF_MUSHROOM_BREAK =
            register("block.shelf_mushroom.break");
    public static final ModRegister.Entry<SoundEvent> SHELF_MUSHROOM_PLACE =
            register("block.shelf_mushroom.place");
    public static final ModRegister.Entry<SoundEvent> SHELF_MUSHROOM_STEP =
            register("block.shelf_mushroom.step");

    public static final ModRegister.Entry<SoundEvent> RED_SHRUB_BREAK =
            register("block.red_shrub.break");
    public static final ModRegister.Entry<SoundEvent> RED_SHRUB_PLACE =
            register("block.red_shrub.place");

    public static final ModRegister.Entry<SoundEvent> STRAW_BED_BREAK =
            register("block.straw_bed.break");
    public static final ModRegister.Entry<SoundEvent> STRAW_BED_BREAK_LEAVE =
            register("block.straw_bed.break_leave");
    public static final ModRegister.Entry<SoundEvent> STRAW_BED_STEP =
            register("block.straw_bed.step");
    public static final ModRegister.Entry<SoundEvent> STRAW_BED_PLACE =
            register("block.straw_bed.place");
    public static final ModRegister.Entry<SoundEvent> STRAW_BED_HIT =
            register("block.straw_bed.hit");
    public static final ModRegister.Entry<SoundEvent> STRAW_BED_FALL =
            register("block.straw_bed.fall");

    public static final ModRegister.Entry<SoundEvent> CUSHION_BREAK =
            register("entity.cushion.break");
    public static final ModRegister.Entry<SoundEvent> CUSHION_PLACE =
            register("entity.cushion.place");
    public static final ModRegister.Entry<SoundEvent> CUSHION_SIT =
            register("entity.cushion.sit");
    public static final ModRegister.Entry<SoundEvent> CUSHION_GET_UP =
            register("entity.cushion.get_up");

    public static SoundType shelfMushroom() {
        return new SoundType(1.0F, 1.0F,
                SHELF_MUSHROOM_BREAK.get(),
                SHELF_MUSHROOM_STEP.get(),
                SHELF_MUSHROOM_PLACE.get(),
                SHELF_MUSHROOM_STEP.get(),
                SHELF_MUSHROOM_STEP.get());
    }

    public static SoundType redShrub() {
        return new SoundType(1.0F, 1.0F,
                RED_SHRUB_BREAK.get(),
                SoundType.GRASS.getStepSound(),
                RED_SHRUB_PLACE.get(),
                SoundType.GRASS.getHitSound(),
                SoundType.GRASS.getFallSound());
    }

    public static SoundType strawBed() {
        return new SoundType(1.0F, 1.0F,
                STRAW_BED_BREAK.get(),
                STRAW_BED_STEP.get(),
                STRAW_BED_PLACE.get(),
                STRAW_BED_HIT.get(),
                STRAW_BED_FALL.get());
    }

    private static ModRegister.Entry<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, name)));
    }

    public static void register() {
    }

    private ModSounds() {
    }
}
