package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModItems;
import dev.BloodyDreamsWork.backport.registry.ModLootFunctions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

public class ExplorerMapConversionFunction implements LootItemFunction {

    public static final ExplorerMapConversionFunction INSTANCE = new ExplorerMapConversionFunction();

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<ExplorerMapConversionFunction> {

        @Override
        public void serialize(com.google.gson.JsonObject json, ExplorerMapConversionFunction function,
                              com.google.gson.JsonSerializationContext context) {
        }

        @Override
        public ExplorerMapConversionFunction deserialize(com.google.gson.JsonObject json,
                                                         com.google.gson.JsonDeserializationContext context) {
            return INSTANCE;
        }
    }

    private ExplorerMapConversionFunction() {
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext context) {
        if (!stack.is(Items.FILLED_MAP)) {
            return stack;
        }

        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("Decorations", Tag.TAG_LIST)) {
            return stack;
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
            return stack;
        }

        ItemStack map = new ItemStack(
                ModItems.EXPLORER_MAPS.get(ExplorerMapType.BURIED_TREASURE).get(), stack.getCount());
        map.setTag(tag.copy());
        return map;
    }

    @Override
    public LootItemFunctionType getType() {
        return ModLootFunctions.EXPLORER_MAP_CONVERSION.get();
    }
}
