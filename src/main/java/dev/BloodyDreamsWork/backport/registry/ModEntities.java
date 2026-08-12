package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.CushionEntity;
import dev.BloodyDreamsWork.backport.content.PoplarBoat;
import dev.BloodyDreamsWork.backport.content.PoplarChestBoat;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public final class ModEntities {
    public static final ModRegister<EntityType<?>> ENTITY_TYPES =
            ModRegister.create(BuiltInRegistries.ENTITY_TYPE);

    public static final ModRegister.Entry<EntityType<CushionEntity>> CUSHION =
            ENTITY_TYPES.register("cushion", () -> EntityType.Builder
                    .<CushionEntity>of(CushionEntity::new, MobCategory.MISC)
                    .sized(1.0F, CushionEntity.HEIGHT)
                    .passengerAttachments(CushionEntity.HEIGHT)
                    .clientTrackingRange(10)
                    .build(entityKey("cushion")));

    public static final ModRegister.Entry<EntityType<PoplarBoat>> POPLAR_BOAT =
            ENTITY_TYPES.register("poplar_boat", () -> EntityType.Builder
                    .<PoplarBoat>of(PoplarBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
                    .build(entityKey("poplar_boat")));

    public static final ModRegister.Entry<EntityType<PoplarChestBoat>> POPLAR_CHEST_BOAT =
            ENTITY_TYPES.register("poplar_chest_boat", () -> EntityType.Builder
                    .<PoplarChestBoat>of(PoplarChestBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
                    .build(entityKey("poplar_chest_boat")));

    private static ResourceKey<EntityType<?>> entityKey(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Backport.MODID, name));
    }

    public static void register() {
    }

    private ModEntities() {
    }
}
