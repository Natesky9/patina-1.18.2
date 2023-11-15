package com.natesky9.patina.entity.BeePrincess;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BeePrincessModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "beeprincessmodel"), "main");
	private final ModelPart bell;
	private final ModelPart butt;
	private final ModelPart arm;
	private final ModelPart body;
	private final ModelPart stinger;
	private final ModelPart head;

	public BeePrincessModel(ModelPart root) {
		this.bell = root.getChild("bell");
		this.butt = root.getChild("butt");
		this.arm = root.getChild("arm");
		this.body = root.getChild("body");
		this.stinger = root.getChild("stinger");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bell = partdefinition.addOrReplaceChild("bell", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, -4.0F));

		PartDefinition sideside_r1 = bell.addOrReplaceChild("sideside_r1", CubeListBuilder.create().texOffs(46, 22).addBox(-9.0F, 1.0F, -9.0F, 18.0F, 14.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(-5.0F, 1.0F, -13.0F, 10.0F, 14.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -1.0F, -11.0F, 14.0F, 18.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 10.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition butt = partdefinition.addOrReplaceChild("butt", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, -4.0F));

		PartDefinition cube_r1 = butt.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, 12.0F, -5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 13.0F, 10.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = butt.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2156F, 15.5684F, -2.3901F, 1.309F, 0.1745F, 0.0F));

		PartDefinition cube_r3 = butt.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 32).addBox(-3.0F, -4.2426F, -12.4853F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, 10.0F, 0.5236F, 0.1745F, 0.0F));

		PartDefinition cube_r4 = butt.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, 0.0F, -6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2156F, 15.5684F, -2.3901F, 1.309F, -0.1745F, 0.0F));

		PartDefinition cube_r5 = butt.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 42).addBox(2.0F, -4.2426F, -12.4853F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 13.0F, 10.0F, 0.5236F, -0.1745F, 0.0F));

		PartDefinition cube_r6 = butt.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(46, 54).addBox(-6.0F, 0.0F, -9.0F, 12.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 10.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create(), PartPose.offset(-9.4672F, -28.7072F, 2.4061F));

		PartDefinition cube_r7 = arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(38, 46).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.4672F, 11.7072F, -0.4061F, -0.1745F, 0.0F, 1.309F));

		PartDefinition cube_r8 = arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(42, 4).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.8886F, 13.0201F, -6.2655F, -0.2444F, -0.7703F, 1.4809F));

		PartDefinition cube_r9 = arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(42, 0).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.2688F, 17.6502F, -7.0302F, -0.1806F, 0.2577F, 1.2625F));

		PartDefinition cube_r10 = arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(56, 48).addBox(-8.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4672F, 11.7072F, -0.4061F, -0.1745F, 0.0F, -1.309F));

		PartDefinition cube_r11 = arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(56, 18).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3345F, 17.6502F, -7.0302F, -0.1806F, -0.2577F, -1.2625F));

		PartDefinition cube_r12 = arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(74, 46).addBox(-8.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0458F, 13.0201F, -6.2655F, -0.2444F, 0.7703F, -1.4809F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 64).addBox(-5.0F, -33.0F, -2.0F, 10.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(56, 0).addBox(-7.0F, -41.0F, -2.0F, 14.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -29.5F, 4.0F, -0.3622F, -0.7519F, 0.2533F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(8, 37).addBox(-7.0F, -0.5F, 1.9F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9284F, -29.4556F, 4.1657F, -1.5708F, -1.309F, 1.5708F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(52, 8).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.3284F, -30.7497F, -0.6639F, -1.5708F, 1.309F, -1.5708F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(56, 46).addBox(0.0F, -0.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -29.5F, 4.0F, -0.3622F, 0.7519F, -0.2533F));

		PartDefinition stinger = partdefinition.addOrReplaceChild("stinger", CubeListBuilder.create(), PartPose.offset(-1.5F, 11.3185F, 7.3277F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 76).addBox(-4.0F, -7.8264F, -5.4997F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -17.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(3.0F, -17.7816F, 1.2882F));

		PartDefinition cube_r17 = eyes.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(6, 40).addBox(-1.8561F, 10.9552F, -0.7493F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, -7.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r18 = eyes.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(42, 8).addBox(-2.1439F, 10.9552F, -0.7493F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition antenna = head.addOrReplaceChild("antenna", CubeListBuilder.create(), PartPose.offset(3.9576F, -24.8277F, -0.4088F));

		PartDefinition cube_r19 = antenna.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-4.2454F, 6.196F, -9.1031F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9153F, 0.0F, -3.6061F, 0.7854F, 0.0F, -0.3054F));

		PartDefinition cube_r20 = antenna.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(8, 0).addBox(-4.7454F, 7.3995F, -4.3524F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9576F, 4.5461F, -0.803F, 0.2618F, 0.0F, -0.3054F));

		PartDefinition cube_r21 = antenna.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 7).addBox(3.2454F, 6.196F, -9.1031F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.6061F, 0.7854F, 0.0F, 0.3054F));

		PartDefinition cube_r22 = antenna.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(8, 7).addBox(3.7454F, 7.3995F, -4.3524F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9576F, 4.5461F, -0.803F, 0.2618F, 0.0F, 0.3054F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bell.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		butt.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		stinger.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}