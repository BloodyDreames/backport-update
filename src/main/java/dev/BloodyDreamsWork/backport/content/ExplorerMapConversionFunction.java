package dev.BloodyDreamsWork.backport.content;

import com.mojang.serialization.MapCodec;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import dev.BloodyDreamsWork.backport.registry.ModLootFunctions;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.MapDecorations;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

public class ExplorerMapConversionFunction implements LootItemFunction {

    public static final ExplorerMapConversionFunction INSTANCE = new ExplorerMapConversionFunction();
    public static final MapCodec<ExplorerMapConversionFunction> CODEC = MapCodec.unit(INSTANCE);

    private ExplorerMapConversionFunction() {
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext context) {
        if (!stack.is(Items.FILLED_MAP)) {
            return stack;
        }

        MapDecorations decorations = stack.get(DataComponents.MAP_DECORATIONS);
        if (decorations == null) {
            return stack;
        }

        boolean treasure = decorations.decorations().values().stream()
                .anyMatch(entry -> entry.type().value() == MapDecorationTypes.RED_X.value());
        if (!treasure) {
            return stack;
        }

        ItemStack map = new ItemStack(
                ModItems.EXPLORER_MAPS.get(ExplorerMapType.BURIED_TREASURE).get(), stack.getCount());
        map.applyComponents(stack.getComponents());
        return map;
    }

    @Override
    public LootItemFunctionType<? extends LootItemFunction> getType() {
        return ModLootFunctions.EXPLORER_MAP_CONVERSION.get();
    }
}
