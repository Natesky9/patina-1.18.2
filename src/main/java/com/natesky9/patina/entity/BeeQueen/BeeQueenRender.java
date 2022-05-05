package com.natesky9.patina.entity.BeeQueen;

import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;

public class BeeQueenRender extends MobRenderer<BeeQueen, BeeQueenModel<BeeQueen>> {
    protected static ResourceLocation BEE1 =
            new ResourceLocation(Patina.MOD_ID,"textures/models/entity/bee_queen.png");
    protected static ResourceLocation BEE2 =
            new ResourceLocation(Patina.MOD_ID,"textures/models/entity/bee_queen_hurt.png");
    protected static ResourceLocation BEE3 =
            new ResourceLocation(Patina.MOD_ID,"textures/models/entity/bee_queen_damaged.png");
    protected static ResourceLocation BEE4 =
            new ResourceLocation(Patina.MOD_ID,"textures/models/entity/bee_queen_injured.png");


    public BeeQueenRender(Context context) {
        super(context,new BeeQueenModel<>(context.bakeLayer(BeeQueenModel.LAYER_LOCATION)),1F);
        this.addLayer(new BeeQueenAura(this,context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(BeeQueen pEntity)
    {
        float getHealth = pEntity.getHealth()/pEntity.getMaxHealth();
        if (getHealth > .75) return BEE1;
        if (getHealth > .5) return BEE2;
        if (getHealth > .25) return BEE3;
        return BEE4;
    }

}
