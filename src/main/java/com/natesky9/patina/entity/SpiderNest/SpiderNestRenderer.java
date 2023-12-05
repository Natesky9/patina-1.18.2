package com.natesky9.patina.entity.SpiderNest;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpiderNestRenderer extends MobRenderer<Spidernest, SpiderNestModel<Spidernest>> {
    protected static ResourceLocation TEXTURE = new ResourceLocation(Patina.MODID, "textures/models/entity/spider_nest.png");

    public SpiderNestRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderNestModel<>(context.bakeLayer(SpiderNestModel.LAYER_LOCATION)), 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(Spidernest entity) {
        return TEXTURE;
    }
}
