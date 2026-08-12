package dev.BloodyDreamsWork.backport.worldgen;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.BloodyDreamsWork.backport.registry.ModRegister;

public final class ModWorldgenTypes {
    public static final ModRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            ModRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE);

    public static final ModRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            ModRegister.create(BuiltInRegistries.TRUNK_PLACER_TYPE);

    public static final ModRegister.Entry<FoliagePlacerType<PoplarFoliagePlacer>> POPLAR_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("poplar_foliage_placer",
                    () -> new FoliagePlacerType<>(PoplarFoliagePlacer.CODEC));

    public static final ModRegister.Entry<TrunkPlacerType<PoplarTrunkPlacer>> POPLAR_TRUNK_PLACER =
            TRUNK_PLACERS.register("poplar_trunk_placer",
                    () -> new TrunkPlacerType<>(PoplarTrunkPlacer.CODEC));

    public static final ModRegister<TreeDecoratorType<?>> TREE_DECORATORS =
            ModRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE);

    public static final ModRegister.Entry<TreeDecoratorType<ShelfMushroomDecorator>> SHELF_MUSHROOM_DECORATOR =
            TREE_DECORATORS.register("shelf_mushroom",
                    () -> new TreeDecoratorType<>(ShelfMushroomDecorator.CODEC));

    public static final ModRegister.Entry<TreeDecoratorType<AttachedToLogsDecorator>> ATTACHED_TO_LOGS_DECORATOR =
            TREE_DECORATORS.register("attached_to_logs",
                    () -> new TreeDecoratorType<>(AttachedToLogsDecorator.CODEC));

    public static final ModRegister<Feature<?>> FEATURES =
            ModRegister.create(BuiltInRegistries.FEATURE);

    public static final ModRegister.Entry<FallenTreeFeature> FALLEN_TREE =
            FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));

    public static void register() {
    }

    private ModWorldgenTypes() {
    }
}
