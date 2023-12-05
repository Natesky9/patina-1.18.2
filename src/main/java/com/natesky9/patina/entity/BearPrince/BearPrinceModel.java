package com.natesky9.patina.entity.BearPrince;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class BearPrinceModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "bearprince"), "main");
	private final ModelPart standing;

	public BearPrinceModel(ModelPart root) {
		this.standing = root.getChild("standing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition standing = partdefinition.addOrReplaceChild("standing", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = standing.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 50).addBox(3.0F, -8.0F, -3.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(65, 0).addBox(-3.0F, -3.0F, -11.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(-6.0F, -8.0F, -3.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 34).addBox(-5.0F, -7.0F, -7.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -37.0F, -1.0F));

		PartDefinition body = standing.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -22.0F, -4.5F, 14.0F, 24.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(30, 58).addBox(-5.0F, -11.0F, -6.5F, 10.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -1.5F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 0).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, -5.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 6.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition armright = standing.addOrReplaceChild("armright", CubeListBuilder.create().texOffs(56, 52).addBox(-6.0F, -2.0F, -4.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -35.0F, -1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition hand = armright.addOrReplaceChild("hand", CubeListBuilder.create().texOffs(50, 66).addBox(-1.0F, 0.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 6.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = hand.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(66, 66).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, -3.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition armleft = standing.addOrReplaceChild("armleft", CubeListBuilder.create().texOffs(8, 50).addBox(3.0F, -2.0F, -4.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -35.0F, -1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition hand2 = armleft.addOrReplaceChild("hand2", CubeListBuilder.create().texOffs(66, 24).addBox(-1.0F, 0.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 6.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = hand2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(65, 12).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, -3.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition legright = standing.addOrReplaceChild("legright", CubeListBuilder.create().texOffs(48, 12).addBox(-4.0F, 11.0F, -5.0F, 4.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(36, 42).addBox(-5.0F, -3.0F, -4.0F, 5.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -14.0F, -1.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r5 = legright.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(8, 64).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, -3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition legleft = standing.addOrReplaceChild("legleft", CubeListBuilder.create().texOffs(48, 0).addBox(2.0F, 11.0F, -5.0F, 4.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(40, 26).addBox(2.0F, -3.0F, -4.0F, 5.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -14.0F, -1.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r6 = legleft.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(60, 36).addBox(1.0F, 0.0F, 0.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.0F, -3.0F, 0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Player player = Minecraft.getInstance().player;
		if (player.hasEffect(MobEffects.NIGHT_VISION))
		{
			System.out.println("has vision");
			standing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		else
		{
			standing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, .1f);
		}
	}
}