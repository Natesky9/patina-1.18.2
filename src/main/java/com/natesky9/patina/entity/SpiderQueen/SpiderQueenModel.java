package com.natesky9.patina.entity.SpiderQueen;// Made with Blockbench 4.8.3
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

public class SpiderQueenModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "spiderqueenmodel"), "main");
	private final ModelPart bone;
	private final ModelPart bone5;
	private final ModelPart bone2;
	private final ModelPart bone6;
	private final ModelPart bone3;
	private final ModelPart bone7;
	private final ModelPart bone4;
	private final ModelPart bone8;
	private final ModelPart butt;
	private final ModelPart head2;
	private final ModelPart bb_main;

	public SpiderQueenModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.bone5 = root.getChild("bone5");
		this.bone2 = root.getChild("bone2");
		this.bone6 = root.getChild("bone6");
		this.bone3 = root.getChild("bone3");
		this.bone7 = root.getChild("bone7");
		this.bone4 = root.getChild("bone4");
		this.bone8 = root.getChild("bone8");
		this.butt = root.getChild("butt");
		this.head2 = root.getChild("head2");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 56).addBox(3.1632F, -0.1737F, -8.7601F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 13.0F, -6.0F, -0.3663F, 0.5553F, -0.6291F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 64).addBox(6.1795F, -0.394F, -4.7601F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.1603F, -1.3301F, -4.0F, 0.0F, 0.0F, 1.5272F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(3.6526F, 5.0F, -4.7601F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, -6.3301F, -4.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 56).addBox(-11.1632F, -0.1737F, -8.7601F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 13.0F, -6.0F, -0.3663F, -0.5553F, 0.6291F));

		PartDefinition cube_r3 = bone5.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 64).addBox(-26.1795F, -0.394F, -4.7601F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.1603F, -1.3301F, -4.0F, 0.0F, 0.0F, -1.5272F));

		PartDefinition cube_r4 = bone5.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-13.6526F, 5.0F, -4.7601F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -6.3301F, -4.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 56).addBox(-1.1551F, 3.1066F, -5.7956F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 8.0F, -5.0F, -0.1332F, 0.2261F, -0.5387F));

		PartDefinition cube_r5 = bone2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(1.5529F, 5.0F, -5.7956F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r6 = bone2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 64).addBox(5.0513F, 1.3768F, -5.7956F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.6603F, 3.0F, 0.0F, 0.0F, 0.0F, 1.5272F));

		PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 56).addBox(-6.8449F, 3.1066F, -5.7956F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 8.0F, -5.0F, -0.1332F, -0.2261F, 0.5387F));

		PartDefinition cube_r7 = bone6.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-11.5529F, 5.0F, -5.7956F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r8 = bone6.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 64).addBox(-25.0513F, 1.3768F, -5.7956F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.6603F, 3.0F, 0.0F, 0.0F, 0.0F, -1.5272F));

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 56).addBox(-3.6247F, 1.6808F, -5.8578F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 8.0F, 0.0F, 0.1104F, -0.1886F, -0.534F));

		PartDefinition cube_r9 = bone3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.2986F, 5.0F, -5.8578F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r10 = bone3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 64).addBox(3.5192F, 3.7818F, -5.8578F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.6603F, 3.0F, 0.0F, 0.0F, 0.0F, 1.5272F));

		PartDefinition bone7 = partdefinition.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 56).addBox(-4.3753F, 1.6808F, -5.8578F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 8.0F, 0.0F, 0.1104F, 0.1886F, 0.534F));

		PartDefinition cube_r11 = bone7.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-8.7014F, 5.0F, -5.8578F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r12 = bone7.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 64).addBox(-23.5192F, 3.7818F, -5.8578F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.6603F, 3.0F, 0.0F, 0.0F, 0.0F, -1.5272F));

		PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 56).addBox(-2.196F, -3.2679F, -5.4378F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 13.0F, 5.0F, 0.2291F, -0.3747F, -0.5672F));

		PartDefinition cube_r13 = bone4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 0).addBox(4.4643F, -5.0F, -5.4378F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5622F, -1.1699F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r14 = bone4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 64).addBox(2.8545F, 4.8251F, -5.4378F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.1603F, -1.3301F, 0.0F, 0.0F, 0.0F, 1.5272F));

		PartDefinition bone8 = partdefinition.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 56).addBox(-5.804F, -3.2679F, -5.4378F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 13.0F, 5.0F, 0.2291F, 0.3747F, 0.5672F));

		PartDefinition cube_r15 = bone8.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-14.4643F, -5.0F, -5.4378F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5622F, -1.1699F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r16 = bone8.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 64).addBox(-22.8545F, 4.8251F, -5.4378F, 20.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.1603F, -1.3301F, 0.0F, 0.0F, 0.0F, -1.5272F));

		PartDefinition butt = partdefinition.addOrReplaceChild("butt", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, 32.0F));

		PartDefinition cube_r17 = butt.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 70).addBox(-20.0F, -30.0F, -20.0F, 24.0F, 32.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-24.0F, -26.0F, -24.0F, 32.0F, 24.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 33.0F, -6.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition head2 = partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(96, 0).addBox(-5.0F, -20.0F, -24.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(1.0F, -19.0F, -25.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-4.0F, -19.0F, -25.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition fang = head2.addOrReplaceChild("fang", CubeListBuilder.create().texOffs(10, 14).addBox(-1.2941F, 0.0F, -7.8296F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, -18.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r18 = fang.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(10, 14).addBox(-1.2941F, -2.1993F, -4.3195F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.1677F, -2.9853F, 0.4363F, 0.0F, 0.0F));

		PartDefinition fang2 = head2.addOrReplaceChild("fang2", CubeListBuilder.create().texOffs(10, 14).addBox(-1.7059F, 0.0F, -7.8296F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, -18.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r19 = fang2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(10, 14).addBox(-1.7059F, -2.1993F, -4.3195F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.1677F, -2.9853F, 0.4363F, 0.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(72, 59).addBox(-8.0F, -23.0F, -16.0F, 16.0F, 12.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int int1, int int2, int int3) {
		bone.render(poseStack, vertexConsumer, int1, int2, int3);
		bone5.render(poseStack, vertexConsumer, int1, int2, int3);
		bone2.render(poseStack, vertexConsumer, int1, int2, int3);
		bone6.render(poseStack, vertexConsumer, int1, int2, int3);
		bone3.render(poseStack, vertexConsumer, int1, int2, int3);
		bone7.render(poseStack, vertexConsumer, int1, int2, int3);
		bone4.render(poseStack, vertexConsumer, int1, int2, int3);
		bone8.render(poseStack, vertexConsumer, int1, int2, int3);
		butt.render(poseStack, vertexConsumer, int1, int2, int3);
		head2.render(poseStack, vertexConsumer, int1, int2, int3);
		bb_main.render(poseStack, vertexConsumer, int1, int2, int3);
	}
}