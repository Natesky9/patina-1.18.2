package com.natesky9.patina.entity.SpiderNest;
//uhh, meow?

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

public class SpiderNestModel<S extends Entity> extends EntityModel<S> {
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(new ResourceLocation(Patina.MOD_ID, "spidernestmodel"), "main");

	private final ModelPart bot;
	private final ModelPart top;
	private final ModelPart mid;
	public SpiderNestModel(ModelPart root)
	{
		this.top = root.getChild("top");
		this.mid = root.getChild("mid");
		this.bot = root.getChild("bot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition bot = partDefinition.addOrReplaceChild("bot", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-5.0F, -7.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition top = partDefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(24, 16)
				.addBox(-2.0F, -11.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition mid = partDefinition.addOrReplaceChild("mid", CubeListBuilder.create().texOffs(0, 16)
				.addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(S pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bot.render(poseStack, buffer, packedLight, packedOverlay);
		top.render(poseStack, buffer, packedLight, packedOverlay);
		mid.render(poseStack, buffer, packedLight, packedOverlay);
	}
}