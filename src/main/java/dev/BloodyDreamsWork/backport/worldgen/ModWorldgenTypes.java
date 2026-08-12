package dev.BloodyDreamsWork.backport.worldgen;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModWorldgenTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Backport.MODID);

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Backport.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<PoplarFoliagePlacer>> POPLAR_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("poplar_foliage_placer",
                    () -> new FoliagePlacerType<>(PoplarFoliagePlacer.CODEC));

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<PoplarTrunkPlacer>> POPLAR_TRUNK_PLACER =
            TRUNK_PLACERS.register("poplar_trunk_placer",
                    () -> new TrunkPlacerType<>(PoplarTrunkPlacer.CODEC));

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Backport.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<ShelfMushroomDecorator>> SHELF_MUSHROOM_DECORATOR =
            TREE_DECORATORS.register("shelf_mushroom",
                    () -> new TreeDecoratorType<>(ShelfMushroomDecorator.CODEC));

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<AttachedToLogsDecorator>> ATTACHED_TO_LOGS_DECORATOR =
            TREE_DECORATORS.register("attached_to_logs",
                    () -> new TreeDecoratorType<>(AttachedToLogsDecorator.CODEC));

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, Backport.MODID);

    public static final DeferredHolder<Feature<?>, FallenTreeFeature> FALLEN_TREE =
            FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));

    public static void register(IEventBus modEventBus) {
        FOLIAGE_PLACERS.register(modEventBus);
        TRUNK_PLACERS.register(modEventBus);
        TREE_DECORATORS.register(modEventBus);
        FEATURES.register(modEventBus);
    }

    private ModWorldgenTypes() {
    }
}
