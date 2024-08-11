package com.natesky9.patina.entity.BearPrince;

import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeRenderTypes;
import org.jetbrains.annotations.Nullable;

public class BearPrinceRenderer extends MobRenderer<BearPrince, BearPrinceModel<BearPrince>> {
    protected static ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Patina.MODID, "textures/models/entity/bear_prince.png");
    //private static final RenderType RENDER_TYPE = RenderType.entityTranslucent(TEXTURE);

    public BearPrinceRenderer(EntityRendererProvider.Context context) {
        super(context, new BearPrinceModel<>(context.bakeLayer(BearPrinceModel.LAYER_LOCATION)), .5F);
    }

    @Override
    public ResourceLocation getTextureLocation(BearPrince entity) {
        return TEXTURE;
    }

    @Nullable
    @Override
    protected RenderType getRenderType(BearPrince p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
        return ForgeRenderTypes.getUnsortedTranslucent(TEXTURE);
    }

    @Override
    public void render(BearPrince p_115455_, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115460_) {
        //this can be removed
        //this.model.renderToBuffer(poseStack, vertexconsumer1, p_115460_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.15F);
        super.render(p_115455_, p_115456_, p_115457_, poseStack, multiBufferSource, p_115460_);
    }
}
