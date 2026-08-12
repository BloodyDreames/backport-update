package dev.BloodyDreamsWork.backport;

import dev.BloodyDreamsWork.backport.content.StrawBedBlock;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Backport.MODID)
public final class ModEvents {
    private static final Map<Block, Block> STRIPPABLES = new IdentityHashMap<>();
    private static final Map<Item, Integer> FURNACE_FUELS = new IdentityHashMap<>();

    public static void addSignBlockEntities() {
        allow(BlockEntityType.SIGN,
                ModBlocks.POPLAR_SIGN.get(),
                ModBlocks.POPLAR_WALL_SIGN.get());
        allow(BlockEntityType.HANGING_SIGN,
                ModBlocks.POPLAR_HANGING_SIGN.get(),
                ModBlocks.POPLAR_WALL_HANGING_SIGN.get());
    }

    private static void allow(BlockEntityType<?> type, Block... blocks) {
        try {
            Field field = BlockEntityType.class.getDeclaredField("validBlocks");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Block> current = (Set<Block>) field.get(type);
            Set<Block> extended = new HashSet<>(current);
            extended.addAll(Arrays.asList(blocks));
            field.set(type, extended);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Failed to extend the block list of " + type, exception);
        }
    }

    public static void registerDataMapReplacements() {
        STRIPPABLES.put(ModBlocks.POPLAR_LOG.get(), ModBlocks.STRIPPED_POPLAR_LOG.get());
        STRIPPABLES.put(ModBlocks.POPLAR_WOOD.get(), ModBlocks.STRIPPED_POPLAR_WOOD.get());

        ComposterBlock.COMPOSTABLES.put(ModItems.RED_POPLAR_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.ORANGE_POPLAR_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_POPLAR_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.POPLAR_SAPLING.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RED_SHRUB.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.SHELF_MUSHROOM.get(), 0.65F);

        FURNACE_FUELS.put(ModItems.POPLAR_SAPLING.get(), 100);
        FURNACE_FUELS.put(ModItems.POPLAR_FENCE.get(), 300);
        FURNACE_FUELS.put(ModItems.POPLAR_FENCE_GATE.get(), 300);
        FURNACE_FUELS.put(ModItems.POPLAR_TRAPDOOR.get(), 300);
        FURNACE_FUELS.put(ModItems.POPLAR_BUTTON.get(), 100);
        FURNACE_FUELS.put(ModItems.POPLAR_PRESSURE_PLATE.get(), 300);
        ModItems.WOOL_STAIRS.values().forEach(item -> FURNACE_FUELS.put(item.get(), 100));
        ModItems.WOOL_SLABS.values().forEach(item -> FURNACE_FUELS.put(item.get(), 50));
    }

    @SubscribeEvent
    public static void stripPoplar(BlockEvent.BlockToolModificationEvent event) {
        if (event.getToolAction() != ToolActions.AXE_STRIP) {
            return;
        }

        Block stripped = STRIPPABLES.get(event.getState().getBlock());
        if (stripped != null) {
            event.setFinalState(stripped.withPropertiesOf(event.getState()));
        }
    }

    @SubscribeEvent
    public static boolean setFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        Integer burnTime = FURNACE_FUELS.get(event.getItemStack().getItem());
        if (burnTime == null) {
            return false;
        }

        event.setBurnTime(burnTime);
        return true;
    }

    @SubscribeEvent
    public static boolean keepRespawnPoint(PlayerSetSpawnEvent event) {
        var config = event.getConfig();
        if (config == null || !event.getEntity().level().dimension().equals(config.respawnData().dimension())) {
            return false;
        }

        BlockPos spawn = config.respawnData().pos();
        return event.getEntity().level().getBlockState(spawn).getBlock() instanceof StrawBedBlock;
    }

    @SubscribeEvent
    public static void crumbleStrawBed(PlayerWakeUpEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide()) {
            return;
        }

        player.getSleepingPos().ifPresent(pos -> {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof StrawBedBlock) {
                level.scheduleTick(pos, state.getBlock(), 1);
            }
        });
    }

    private ModEvents() {
    }
}
