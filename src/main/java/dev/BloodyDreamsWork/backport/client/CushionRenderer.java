package dev.BloodyDreamsWork.backport.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.CushionEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import com.mojang.math.Axis;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class CushionRenderer extends EntityRenderer<CushionEntity> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Backport.MODID, "cushion"), "main");

    private static final Map<DyeColor, ResourceLocation> TEXTURES = textures();

    private static final double SURFACE_GAP = 0.002;

    private final ModelPart root;

    public CushionRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.root = context.bakeLayer(LAYER);
        this.shadowRadius = 0.5F;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        mesh.getRoot().addOrReplaceChild("cushion",
                CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -4.0F, -8.0F, 16.0F, 4.0F, 16.0F),
                PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void render(CushionEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffers, int light) {
        poseStack.pushPose();
        poseStack.translate(0.0, SURFACE_GAP, 0.0);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - Mth.rotLerp(partialTick, entity.yRotO, entity.getYRot())));
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        VertexConsumer consumer = buffers.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity)));
        this.root.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffers, light);
    }

    @Override
    public ResourceLocation getTextureLocation(CushionEntity entity) {
        return TEXTURES.get(entity.getColor());
    }

    private static Map<DyeColor, ResourceLocation> textures() {
        Map<DyeColor, ResourceLocation> result = new EnumMap<>(DyeColor.class);
        for (DyeColor color : DyeColor.values()) {
            result.put(color, ResourceLocation.fromNamespaceAndPath(Backport.MODID,
                    "textures/entity/cushion/" + color.getName() + "_cushion.png"));
        }
        return Collections.unmodifiableMap(result);
    }
}
