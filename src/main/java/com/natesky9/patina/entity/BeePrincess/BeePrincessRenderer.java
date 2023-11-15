package com.natesky9.patina.entity.BeePrincess;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BeePrincessRenderer extends MobRenderer<BeePrincess, BeePrincessModel<BeePrincess>> {
    protected static ResourceLocation TEXTURE = new ResourceLocation(Patina.MODID, "textures/models/entity/bee_princess.png");

    public BeePrincessRenderer(EntityRendererProvider.Context context) {
        super(context, new BeePrincessModel<>(context.bakeLayer(BeePrincessModel.LAYER_LOCATION)), 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(BeePrincess entity) {
        return TEXTURE;
    }
}
