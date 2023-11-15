package com.natesky9.patina.entity.BearPrince;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BearPrinceRenderer extends MobRenderer<BearPrince, BearPrinceModel<BearPrince>> {
    protected static ResourceLocation TEXTURE = new ResourceLocation(Patina.MODID, "textures/models/entity/bear_prince.png");

    public BearPrinceRenderer(EntityRendererProvider.Context context) {
        super(context, new BearPrinceModel<>(context.bakeLayer(BearPrinceModel.LAYER_LOCATION)), 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(BearPrince entity) {
        return TEXTURE;
    }
}
