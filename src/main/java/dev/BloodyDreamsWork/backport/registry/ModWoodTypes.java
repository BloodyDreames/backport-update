package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.mixin.BlockSetTypeInvoker;
import dev.BloodyDreamsWork.backport.mixin.WoodTypeInvoker;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public final class ModWoodTypes {
    public static final BlockSetType POPLAR_SET =
            BlockSetTypeInvoker.backport$register(new BlockSetType(Backport.MODID + ":poplar"));

    public static final WoodType POPLAR =
            WoodTypeInvoker.backport$register(new WoodType(Backport.MODID + ":poplar", POPLAR_SET));

    static void init() {
    }

    public static void touch() {
    }

    private ModWoodTypes() {
    }
}
