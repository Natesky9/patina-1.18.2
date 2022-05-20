package com.natesky9.patina.entity.SpiderQueen;// Made with Blockbench 4.2.3
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

public class SpiderQueenModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Patina.MOD_ID, "spiderqueenmodel"), "main");
	private final ModelPart leftleg2;
	private final ModelPart butt;
	private final ModelPart rightleg2;
	private final ModelPart head2;
	private final ModelPart bb_main;

	public SpiderQueenModel(ModelPart root) {
		this.leftleg2 = root.getChild("leftleg2");
		this.butt = root.getChild("butt");
		this.rightleg2 = root.getChild("rightleg2");
		this.head2 = root.getChild("head2");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftleg2 = partdefinition.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(0, 18).addBox(8.2929F, 1.2929F, 1.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.364F, -1.7782F, -6.0F));

		PartDefinition cube_r1 = leftleg2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.5708F, 1.2929F, 13.485F, -0.1791F, -0.192F, 0.7591F));

		PartDefinition cube_r2 = leftleg2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 24).addBox(-0.2218F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1005F, 9.9289F, 8.0F, 0.1872F, -0.1841F, -0.8027F));

		PartDefinition cube_r3 = leftleg2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -2.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.116F, 1.2929F, 9.3439F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r4 = leftleg2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.2929F, 1.2929F, 3.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition cube_r5 = leftleg2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 24).addBox(-0.2218F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1005F, 9.9289F, 3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r6 = leftleg2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.4537F, 1.2929F, 23.5962F, -0.3719F, -0.3775F, 0.8136F));

		PartDefinition cube_r7 = leftleg2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 24).addBox(-0.2218F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1005F, 9.9289F, 13.0F, 0.3876F, -0.3614F, -0.8571F));

		PartDefinition cube_r8 = leftleg2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -2.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5973F, 1.2929F, 15.5962F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r9 = leftleg2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -2.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5973F, 1.2929F, -4.5962F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r10 = leftleg2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 24).addBox(-0.2218F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1005F, 9.9289F, -2.0F, -0.3876F, 0.3614F, -0.8571F));

		PartDefinition cube_r11 = leftleg2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.4537F, 1.2929F, -12.5962F, 0.3719F, 0.3775F, 0.8136F));

		PartDefinition butt = partdefinition.addOrReplaceChild("butt", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, 32.0F));

		PartDefinition cube_r12 = butt.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 0).addBox(-20.0F, -2.0F, -20.0F, 24.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-20.0F, 26.0F, -20.0F, 24.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 72).addBox(-24.0F, 2.0F, -24.0F, 32.0F, 24.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rightleg2 = partdefinition.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(0, 18).addBox(-24.2929F, 1.2929F, 1.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.364F, -1.7782F, -6.0F));

		PartDefinition cube_r13 = rightleg2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 30).addBox(-32.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.5708F, 1.2929F, 13.485F, -0.1791F, 0.192F, -0.7591F));

		PartDefinition cube_r14 = rightleg2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 24).addBox(-9.7782F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1005F, 9.9289F, 8.0F, 0.1872F, 0.1841F, 0.8027F));

		PartDefinition cube_r15 = rightleg2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 18).addBox(-16.0F, 0.0F, -2.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.116F, 1.2929F, 9.3439F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r16 = rightleg2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 30).addBox(-32.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.2929F, 1.2929F, 3.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition cube_r17 = rightleg2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 24).addBox(-9.7782F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1005F, 9.9289F, 3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r18 = rightleg2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 30).addBox(-32.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.4537F, 1.2929F, 23.5962F, -0.3719F, 0.3775F, -0.8136F));

		PartDefinition cube_r19 = rightleg2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 24).addBox(-9.7782F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1005F, 9.9289F, 13.0F, 0.3876F, 0.3614F, 0.8571F));

		PartDefinition cube_r20 = rightleg2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 18).addBox(-16.0F, 0.0F, -2.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5973F, 1.2929F, 15.5962F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r21 = rightleg2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 18).addBox(-16.0F, 0.0F, -2.0F, 16.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5973F, 1.2929F, -4.5962F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r22 = rightleg2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 24).addBox(-9.7782F, -2.435F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1005F, 9.9289F, -2.0F, -0.3876F, -0.3614F, 0.8571F));

		PartDefinition cube_r23 = rightleg2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 30).addBox(-32.0F, 0.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.4537F, 1.2929F, -12.5962F, 0.3719F, -0.3775F, -0.8136F));

		PartDefinition head2 = partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -25.0F, -18.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(36, 7).addBox(-5.0F, -25.0F, -19.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-4.0F, -24.0F, -19.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 18).addBox(-3.5F, -15.0F, -19.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 0).addBox(-4.0F, -19.0F, -20.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(30, 0).mirror().addBox(1.0F, -19.0F, -20.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(36, 18).mirror().addBox(1.5F, -15.0F, -19.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 4).mirror().addBox(1.0F, -24.0F, -19.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(36, 7).mirror().addBox(3.0F, -25.0F, -19.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 36).addBox(-12.0F, -28.0F, -10.0F, 24.0F, 12.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leftleg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		butt.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightleg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}