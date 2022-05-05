package com.natesky9.patina.entity.BeeQueen;// Made with Blockbench 4.2.2
// Exported for Minecraft version 1.17 with Mojang mappings
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

public class BeeQueenModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Patina.MOD_ID, "beequeenmodel"), "main");
	private final ModelPart bell;
	private final ModelPart butt;
	private final ModelPart arm;
	private final ModelPart wings;
	private final ModelPart body;
	private final ModelPart stinger;
	private final ModelPart head;

	public BeeQueenModel(ModelPart root) {
		this.bell = root.getChild("bell");
		this.butt = root.getChild("butt");
		this.arm = root.getChild("arm");
		this.wings = root.getChild("wings");
		this.body = root.getChild("body");
		this.stinger = root.getChild("stinger");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bell = partdefinition.addOrReplaceChild("bell", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, -4.0F));

		PartDefinition sideside_r1 = bell.addOrReplaceChild("sideside_r1", CubeListBuilder.create().texOffs(64, 0).addBox(-10.0F, 2.0F, -2.0F, 20.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, 2.0F, -6.0F, 12.0F, 20.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-8.0F, 0.0F, -4.0F, 16.0F, 24.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition shadow_r1 = bell.addOrReplaceChild("shadow_r1", CubeListBuilder.create().texOffs(93, 33).addBox(-2.5F, -12.5F, 5.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(5.0F)), PartPose.offsetAndRotation(1.5F, 30.1078F, 5.4792F, 0.2618F, 0.0F, 0.0F));

		PartDefinition butt = partdefinition.addOrReplaceChild("butt", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, -4.0F));

		PartDefinition cube_r1 = butt.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(102, 32).addBox(-2.0F, 15.0F, 3.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = butt.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(96, 121).addBox(-3.0F, -9.9169F, 8.6913F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.1745F, 0.0F));

		PartDefinition cube_r3 = butt.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(101, 122).addBox(-3.0F, -4.2426F, 8.4853F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, -0.1745F, 0.0F));

		PartDefinition cube_r4 = butt.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(96, 121).mirror().addBox(2.0F, -9.9169F, 8.6913F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.1745F, 0.0F));

		PartDefinition cube_r5 = butt.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(101, 122).mirror().addBox(2.0F, -4.2426F, 8.4853F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.1745F, 0.0F));

		PartDefinition cube_r6 = butt.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(84, 103).addBox(-6.0F, 0.0F, -1.0F, 12.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create(), PartPose.offset(-9.4672F, -28.7072F, 2.4061F));

		PartDefinition cube_r7 = arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(44, 16).addBox(-8.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4672F, -1.2928F, -2.4061F, 0.1745F, 0.0F, -1.309F));

		PartDefinition cube_r8 = arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 4).addBox(-8.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0458F, 0.0201F, 3.4532F, 0.2444F, -0.7703F, -1.4809F));

		PartDefinition cube_r9 = arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3345F, 4.6502F, 4.2179F, 0.1806F, 0.2577F, -1.2625F));

		PartDefinition cube_r10 = arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(56, 0).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.4672F, -1.2928F, -2.4061F, 0.1745F, 0.0F, 1.309F));

		PartDefinition cube_r11 = arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(48, 50).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.2688F, 4.6502F, 4.2179F, 0.1806F, -0.2577F, 1.2625F));

		PartDefinition cube_r12 = arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(28, 88).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.8886F, 0.0201F, 3.4532F, 0.2444F, 0.7703F, 1.4809F));

		PartDefinition wings = partdefinition.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(6.0F, -26.0F, -5.0F));

		PartDefinition cube_r13 = wings.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, -4.5F, -26.0F, 0.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, 0.5F, 0.0F, -0.5387F, 0.2261F, -0.1332F));

		PartDefinition cube_r14 = wings.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(32, 64).addBox(0.0F, -4.5F, -26.0F, 0.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, -0.5387F, -0.2261F, 0.1332F));

		PartDefinition cube_r15 = wings.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -4.0F, -10.0F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, 0.0F, 0.0F, -0.5387F, 0.2261F, -0.1332F));

		PartDefinition cube_r16 = wings.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -4.0F, -10.0F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5387F, -0.2261F, 0.1332F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(64, 50).addBox(-5.0F, -48.0F, -4.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(54, 32).addBox(-7.0F, -56.0F, -6.0F, 14.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(124, 126).mirror().addBox(0.0F, -0.5F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -44.5F, -2.0F, 0.3622F, -0.7519F, -0.2533F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(124, 126).mirror().addBox(3.0F, -0.5F, -2.9F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9284F, -44.4556F, -2.1657F, 1.5708F, -1.309F, -1.5708F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(124, 126).addBox(-7.0F, -0.5F, -2.9F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9284F, -44.4556F, -2.1657F, 1.5708F, 1.309F, 1.5708F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(124, 126).addBox(-4.0F, -0.5F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -44.5F, -2.0F, 0.3622F, 0.7519F, 0.2533F));

		PartDefinition stinger = partdefinition.addOrReplaceChild("stinger", CubeListBuilder.create(), PartPose.offset(-1.5F, 11.3185F, 7.3277F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(58, 82).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, -3.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(3.0F, -5.5F, 6.5F));

		PartDefinition cube_r21 = eyes.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(60, 4).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r22 = eyes.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(88, 66).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition antenna = head.addOrReplaceChild("antenna", CubeListBuilder.create(), PartPose.offset(3.9576F, -12.5461F, 4.803F));

		PartDefinition cube_r23 = antenna.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(48, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.3054F));

		PartDefinition cube_r24 = antenna.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(48, 40).addBox(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9576F, 4.5461F, -2.803F, -0.2618F, 0.0F, 0.3054F));

		PartDefinition cube_r25 = antenna.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(60, 9).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9153F, 0.0F, 0.0F, -0.7854F, 0.0F, -0.3054F));

		PartDefinition cube_r26 = antenna.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(67, 53).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9576F, 4.5461F, -2.803F, -0.2618F, 0.0F, -0.3054F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bell.render(poseStack, buffer, packedLight, packedOverlay);
		butt.render(poseStack, buffer, packedLight, packedOverlay);
		arm.render(poseStack, buffer, packedLight, packedOverlay);
		wings.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		stinger.render(poseStack, buffer, packedLight, packedOverlay);
		head.render(poseStack, buffer, packedLight, packedOverlay);
	}
}