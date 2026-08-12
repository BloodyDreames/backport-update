package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.level.Level;

public class PoplarChestBoat extends ChestBoat {

    public PoplarChestBoat(EntityType<? extends ChestBoat> type, Level level) {
        super(type, level, ModItems.POPLAR_CHEST_BOAT::get);
    }

    public PoplarChestBoat(Level level, double x, double y, double z) {
        this(ModEntities.POPLAR_CHEST_BOAT.get(), level);
        this.setInitialPos(x, y, z);
    }
}
