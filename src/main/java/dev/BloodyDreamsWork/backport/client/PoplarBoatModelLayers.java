package dev.BloodyDreamsWork.backport.client;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public final class PoplarBoatModelLayers {

    public static final ModelLayerLocation BOAT =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(Backport.MODID, "boat/poplar"), "main");
    public static final ModelLayerLocation CHEST_BOAT =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(Backport.MODID, "chest_boat/poplar"), "main");

    private PoplarBoatModelLayers() {
    }
}
