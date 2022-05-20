package com.natesky9.patina.entity.SpiderNest;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpiderNestRender extends MobRenderer<SpiderNestEntity, SpiderNestModel<SpiderNestEntity>> {
    protected static final  ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/models/entity/spider_nest.png");

    public SpiderNestRender(EntityRendererProvider.Context context) {
        super(context,new SpiderNestModel<>(context.bakeLayer(SpiderNestModel.LAYER_LOCATION)),1F);
    }


    @Override
    public ResourceLocation getTextureLocation(SpiderNestEntity pEntity) {
        return TEXTURE;
    }
}
