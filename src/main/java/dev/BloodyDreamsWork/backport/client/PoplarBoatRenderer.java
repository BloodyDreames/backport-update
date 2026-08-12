package dev.BloodyDreamsWork.backport.client;

import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

public class PoplarBoatRenderer extends BoatRenderer {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("backport", "textures/entity/boat/poplar.png");
    private static final ResourceLocation CHEST_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("backport", "textures/entity/chest_boat/poplar.png");

    private final boolean hasChest;

    public PoplarBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, hasChest);
        this.hasChest = hasChest;
    }

    @Override
    public ResourceLocation getTextureLocation(Boat entity) {
        return this.hasChest ? CHEST_TEXTURE : TEXTURE;
    }
}
