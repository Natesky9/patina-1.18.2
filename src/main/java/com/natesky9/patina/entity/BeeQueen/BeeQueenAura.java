package com.natesky9.patina.entity.BeeQueen;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BeeQueenAura extends EnergySwirlLayer<BeeQueen, BeeQueenModel<BeeQueen>>
{
    private static final ResourceLocation BeeQueenArmor = new ResourceLocation("textures/entity/wither/wither_armor.png");
    private final BeeQueenModel<BeeQueen> model;


    public BeeQueenAura(RenderLayerParent<BeeQueen, BeeQueenModel<BeeQueen>> model, EntityModelSet modelSet)
    {
        super(model);
        this.model = new BeeQueenModel<>(modelSet.bakeLayer(BeeQueenModel.LAYER_LOCATION));
    }


    @Override
    protected float xOffset(float p_225634_1_) {
        return 0;
    }

    @Override
    protected ResourceLocation getTextureLocation() {return BeeQueenArmor;}

    @Override
    protected EntityModel<BeeQueen> model() {
        return model;
    }
}
