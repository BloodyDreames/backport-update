package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;

public class PoplarBoat extends Boat {

    public PoplarBoat(EntityType<? extends Boat> type, Level level) {
        super(type, level, ModItems.POPLAR_BOAT::get);
    }

    public PoplarBoat(Level level, double x, double y, double z) {
        this(ModEntities.POPLAR_BOAT.get(), level);
        this.setInitialPos(x, y, z);
    }
}
