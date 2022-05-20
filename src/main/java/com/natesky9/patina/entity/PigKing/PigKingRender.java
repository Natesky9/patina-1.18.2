package com.natesky9.patina.entity.PigKing;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class PigKingRender extends MobRenderer<PigKing,PigKingModel<PigKing>>
{
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/models/entity/pig_king.png");

    public PigKingRender(Context context)
    {
        super(context,new PigKingModel<>(context.bakeLayer(PigKingModel.LAYER_LOCATION)),1F);
    }

    @Override
    public ResourceLocation getTextureLocation(PigKing pEntity) {
        return TEXTURE;
    }
}
