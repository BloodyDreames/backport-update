package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.CushionEntity;
import dev.BloodyDreamsWork.backport.content.PoplarBoat;
import dev.BloodyDreamsWork.backport.content.PoplarChestBoat;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Backport.MODID);

    public static final RegistryObject<EntityType<CushionEntity>> CUSHION =
            ENTITY_TYPES.register("cushion", () -> EntityType.Builder
                    .<CushionEntity>of(CushionEntity::new, MobCategory.MISC)
                    .sized(1.0F, CushionEntity.HEIGHT)
                    .clientTrackingRange(10)
                    .build("cushion"));

    public static final RegistryObject<EntityType<PoplarBoat>> POPLAR_BOAT =
            ENTITY_TYPES.register("poplar_boat", () -> EntityType.Builder
                    .<PoplarBoat>of(PoplarBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
                    .build("poplar_boat"));

    public static final RegistryObject<EntityType<PoplarChestBoat>> POPLAR_CHEST_BOAT =
            ENTITY_TYPES.register("poplar_chest_boat", () -> EntityType.Builder
                    .<PoplarChestBoat>of(PoplarChestBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
                    .build("poplar_chest_boat"));

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }

    private ModEntities() {
    }
}
