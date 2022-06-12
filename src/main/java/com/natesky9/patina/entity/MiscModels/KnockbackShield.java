package com.natesky9.patina.entity.MiscModels;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.natesky9.patina.Patina;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class KnockbackShield extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Patina.MOD_ID, "knockback_shield"), "main");
	private final ModelPart root;

	public KnockbackShield(Function<ResourceLocation, RenderType> pRenderType, ModelPart root) {
		super(pRenderType);
		this.root = root;
	}


	public static LayerDefinition createMainLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -20.0F, 7.0F, 12.0F, 20.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 1).addBox(-10.0F, -13.0F, 8.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-10.0F, -18.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-10.0F, -12.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-10.0F, -6.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-3.0F, -20.0F, 6.0F, 1.0F, 20.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-14.0F, -20.0F, 6.0F, 1.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}