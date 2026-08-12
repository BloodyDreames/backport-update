package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PoplarBoat extends Boat {

    public PoplarBoat(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    public PoplarBoat(Level level, double x, double y, double z) {
        this(ModEntities.POPLAR_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Item getDropItem() {
        return ModItems.POPLAR_BOAT.get();
    }
}
