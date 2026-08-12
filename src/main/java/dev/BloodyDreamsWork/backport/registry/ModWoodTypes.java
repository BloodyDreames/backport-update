package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.world.level.block.state.properties.WoodType;

public final class ModWoodTypes {
    public static final WoodType POPLAR =
            WoodType.register(WoodType.create(Backport.MODID + ":poplar"));

    static void init() {
    }

    private ModWoodTypes() {
    }
}
