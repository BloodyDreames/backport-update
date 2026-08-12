package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public final class ModWoodTypes {
    public static final BlockSetType POPLAR_SET =
            BlockSetType.register(new BlockSetType(Backport.MODID + ":poplar"));

    public static final WoodType POPLAR =
            WoodType.register(new WoodType(Backport.MODID + ":poplar", POPLAR_SET));

    static void init() {
    }

    private ModWoodTypes() {
    }
}
