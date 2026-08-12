package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;

public final class ModCreativeTabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab(Backport.MODID + ".main") {
        @Override
        public net.minecraft.world.item.ItemStack makeIcon() {
            return ModItems.POPLAR_LOG.get().getDefaultInstance();
        }
    };

    public static void register(IEventBus modEventBus) {
    }

    private ModCreativeTabs() {
    }
}
