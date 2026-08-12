package dev.BloodyDreamsWork.backport.content;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.MapDecorations;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.Optional;

public class ExplorerMapConversionModifier extends LootModifier {

    public static final MapCodec<ExplorerMapConversionModifier> CODEC = RecordCodecBuilder.mapCodec(
            instance -> codecStart(instance).apply(instance, ExplorerMapConversionModifier::new));

    public ExplorerMapConversionModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(
            LootTable table, ObjectArrayList<ItemStack> loot, LootContext context) {
        for (int i = 0; i < loot.size(); i++) {
            ItemStack stack = loot.get(i);
            if (stack.is(Items.FILLED_MAP)) {
                Optional<ItemStack> converted = convert(stack);
                if (converted.isPresent()) {
                    loot.set(i, converted.get());
                }
            }
        }
        return loot;
    }

    private static Optional<ItemStack> convert(ItemStack stack) {
        MapDecorations decorations = stack.get(DataComponents.MAP_DECORATIONS);
        if (decorations == null) {
            return Optional.empty();
        }

        boolean treasure = decorations.decorations().values().stream()
                .anyMatch(entry -> entry.type().value() == MapDecorationTypes.RED_X.value());
        if (!treasure) {
            return Optional.empty();
        }

        ItemStack map = new ItemStack(
                ModItems.EXPLORER_MAPS.get(ExplorerMapType.BURIED_TREASURE).get(), stack.getCount());
        map.applyComponents(stack.getComponents());
        return Optional.of(map);
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
