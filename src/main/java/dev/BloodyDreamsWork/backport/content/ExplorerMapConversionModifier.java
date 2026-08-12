package dev.BloodyDreamsWork.backport.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Optional;

public class ExplorerMapConversionModifier extends LootModifier {

    public static final Codec<ExplorerMapConversionModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, ExplorerMapConversionModifier::new));

    public ExplorerMapConversionModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> loot, LootContext context) {
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
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("Decorations", Tag.TAG_LIST)) {
            return Optional.empty();
        }

        ListTag decorations = tag.getList("Decorations", Tag.TAG_COMPOUND);
        boolean treasure = false;
        for (int i = 0; i < decorations.size(); i++) {
            if (decorations.getCompound(i).getByte("type") == MapDecoration.Type.RED_X.getIcon()) {
                treasure = true;
                break;
            }
        }
        if (!treasure) {
            return Optional.empty();
        }

        ItemStack map = new ItemStack(
                ModItems.EXPLORER_MAPS.get(ExplorerMapType.BURIED_TREASURE).get(), stack.getCount());
        map.setTag(tag.copy());
        return Optional.of(map);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
