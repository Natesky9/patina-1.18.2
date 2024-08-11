package com.natesky9.patina.entity.SpiderQueen;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpiderQueenRenderer extends MobRenderer<SpiderQueen, SpiderQueenModel<SpiderQueen>> {
    protected static ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Patina.MODID, "textures/models/entity/spider_queen.png");

    public SpiderQueenRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderQueenModel<>(context.bakeLayer(SpiderQueenModel.LAYER_LOCATION)), 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderQueen entity) {
        return TEXTURE;
    }
}
