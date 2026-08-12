package dev.BloodyDreamsWork.backport.content;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.BloodyDreamsWork.backport.registry.ModLootFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ExplorerMapFunction extends LootItemConditionalFunction {

    public static final MapCodec<ExplorerMapFunction> CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(instance.group(
                            TagKey.codec(Registries.STRUCTURE).optionalFieldOf("destination")
                                    .forGetter(function -> function.destination),
                            MapDecoration.Type.CODEC.optionalFieldOf("decoration")
                                    .forGetter(function -> function.decoration)))
                    .apply(instance, ExplorerMapFunction::new));

    private static final byte ZOOM = 2;

    private final Optional<TagKey<Structure>> destination;
    private final Optional<MapDecoration.Type> decoration;

    protected ExplorerMapFunction(List<LootItemCondition> conditions,
                                  Optional<TagKey<Structure>> destination,
                                  Optional<MapDecoration.Type> decoration) {
        super(conditions);
        this.destination = destination;
        this.decoration = decoration;
    }

    @Override
    public LootItemFunctionType getType() {
        return ModLootFunctions.EXPLORER_MAP.get();
    }

    @Override
    public Set<LootContextParam<?>> getReferencedContextParams() {
        return ImmutableSet.of(LootContextParams.ORIGIN);
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        if (!(stack.getItem() instanceof ExplorerMapItem map)) {
            return stack;
        }

        Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
        if (origin == null) {
            return stack;
        }

        ExplorerMapType type = map.type();
        TagKey<Structure> target = this.destination.orElseGet(type::destination);
        MapDecoration.Type marker = this.decoration.orElseGet(type::decoration);

        ServerLevel level = context.getLevel();
        BlockPos found = level.findNearestMapStructure(
                target, BlockPos.containing(origin), ExplorerMapType.SEARCH_RADIUS, true);
        if (found == null) {
            return stack;
        }

        ItemStack filled = MapItem.create(level, found.getX(), found.getZ(), ZOOM, true, true);
        MapItem.renderBiomePreviewMap(level, filled);
        MapItemSavedData.addTargetDecoration(filled, found, "+", marker);

        CompoundTag source = filled.getOrCreateTag();
        CompoundTag target1 = stack.getOrCreateTag();
        if (source.contains("map")) {
            target1.putInt("map", source.getInt("map"));
        }
        Tag decorations = source.get("Decorations");
        if (decorations != null) {
            target1.put("Decorations", decorations.copy());
        }
        return stack;
    }
}
