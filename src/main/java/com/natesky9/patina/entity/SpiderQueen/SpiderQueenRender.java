package com.natesky9.patina.entity.SpiderQueen;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpiderQueenRender extends MobRenderer<SpiderQueen, SpiderQueenModel<SpiderQueen>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/models/entity/spider_queen.png");

    public SpiderQueenRender(Context context)
    {
        super(context,new SpiderQueenModel<>(context.bakeLayer(SpiderQueenModel.LAYER_LOCATION)),1F);
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderQueen pEntity) {
        return TEXTURE;
    }
}
