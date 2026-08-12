package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Backport.MODID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + Backport.MODID + ".main"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> ModItems.POPLAR_LOG.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.POPLAR_LOG.get());
                        output.accept(ModItems.POPLAR_WOOD.get());
                        output.accept(ModItems.STRIPPED_POPLAR_LOG.get());
                        output.accept(ModItems.STRIPPED_POPLAR_WOOD.get());
                        output.accept(ModItems.POPLAR_PLANKS.get());
                        output.accept(ModItems.POPLAR_STAIRS.get());
                        output.accept(ModItems.POPLAR_SLAB.get());
                        output.accept(ModItems.POPLAR_FENCE.get());
                        output.accept(ModItems.POPLAR_FENCE_GATE.get());
                        output.accept(ModItems.POPLAR_DOOR.get());
                        output.accept(ModItems.POPLAR_TRAPDOOR.get());
                        output.accept(ModItems.POPLAR_PRESSURE_PLATE.get());
                        output.accept(ModItems.POPLAR_BUTTON.get());
                        output.accept(ModItems.RED_POPLAR_LEAVES.get());
                        output.accept(ModItems.ORANGE_POPLAR_LEAVES.get());
                        output.accept(ModItems.YELLOW_POPLAR_LEAVES.get());
                        output.accept(ModItems.POPLAR_SAPLING.get());
                        output.accept(ModItems.POPLAR_SIGN.get());
                        output.accept(ModItems.POPLAR_HANGING_SIGN.get());
                        output.accept(ModItems.POPLAR_BOAT.get());
                        output.accept(ModItems.POPLAR_CHEST_BOAT.get());
                        output.accept(ModItems.SHELF_MUSHROOM.get());
                        output.accept(ModItems.RED_SHRUB.get());

                        output.accept(ModItems.STRAW_BED.get());
                        ModItems.CUSHIONS.values().forEach(item -> output.accept(item.get()));

                        ModItems.WOOL_STAIRS.values().forEach(item -> output.accept(item.get()));
                        ModItems.WOOL_SLABS.values().forEach(item -> output.accept(item.get()));
                        ModItems.CONCRETE_STAIRS.values().forEach(item -> output.accept(item.get()));
                        ModItems.CONCRETE_SLABS.values().forEach(item -> output.accept(item.get()));
                    })
                    .build());

    public static void register(BusGroup modBusGroup) {
        TABS.register(modBusGroup);
    }

    private ModCreativeTabs() {
    }
}
