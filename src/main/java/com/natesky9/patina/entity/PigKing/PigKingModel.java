package com.natesky9.patina.entity.PigKing;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.natesky9.patina.Patina;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class PigKingModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Patina.MOD_ID, "pigkingmodel"), "main");
    private final ModelPart bb_main;

    public PigKingModel(ModelPart root)
    {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(16, 36).addBox(-5.0F, -10.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(1.0F, -10.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -18.0F, -5.0F, 12.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-5.0F, -26.0F, -4.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(30, 28).addBox(-3.0F, -32.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(46, 40).addBox(5.0F, -26.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0).addBox(-8.0F, -26.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-1.0F, -30.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 40).addBox(5.0F, -19.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(40, 14).addBox(-8.0F, -19.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 18).addBox(-1.0F, -15.0F, 5.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, -36.0F, -2.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 0).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -32.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}