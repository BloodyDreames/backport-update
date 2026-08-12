package dev.BloodyDreamsWork.backport.registry;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.Supplier;

public final class ModBoatTypes {

    public static final EnumProxy<Boat.Type> POPLAR = new EnumProxy<>(
            Boat.Type.class,
            (Supplier<Block>) () -> ModBlocks.POPLAR_PLANKS.get(),
            "backport:poplar",
            (Supplier<Item>) () -> ModItems.POPLAR_BOAT.get(),
            (Supplier<Item>) () -> ModItems.POPLAR_CHEST_BOAT.get(),
            (Supplier<Item>) () -> Items.STICK,
            false);

    public static Boat.Type poplar() {
        return POPLAR.getValue();
    }

    private ModBoatTypes() {
    }
}
