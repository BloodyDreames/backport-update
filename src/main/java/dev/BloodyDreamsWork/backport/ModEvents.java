package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.mixin.BlockEntityTypeAccessor;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ModEvents {

    public static void register() {
        addSignBlockEntities();
    }

    private static void addSignBlockEntities() {
        allow(BlockEntityType.SIGN,
                ModBlocks.POPLAR_SIGN.get(),
                ModBlocks.POPLAR_WALL_SIGN.get());
        allow(BlockEntityType.HANGING_SIGN,
                ModBlocks.POPLAR_HANGING_SIGN.get(),
                ModBlocks.POPLAR_WALL_HANGING_SIGN.get());
    }

    private static void allow(BlockEntityType<?> type, Block... blocks) {
        BlockEntityTypeAccessor accessor = (BlockEntityTypeAccessor) type;
        Set<Block> extended = new HashSet<>(accessor.backport$getValidBlocks());
        extended.addAll(Arrays.asList(blocks));
        accessor.backport$setValidBlocks(extended);
    }

    private ModEvents() {
    }
}
