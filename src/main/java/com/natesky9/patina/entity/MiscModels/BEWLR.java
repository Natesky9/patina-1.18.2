package com.natesky9.patina.entity.MiscModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.natesky9.patina.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BEWLR extends BlockEntityWithoutLevelRenderer implements ResourceManagerReloadListener {
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final EntityModelSet entityModelSet;

    private KnockbackShield knockbackShield;

    public BEWLR(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
        this.entityModelSet = pEntityModelSet;
    }

    @Override
    public void onResourceManagerReload(ResourceManager pResourceManager)
    {
        this.knockbackShield = new KnockbackShield(
                RenderType::entitySolid,
                this.entityModelSet.bakeLayer(KnockbackShield.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack pStack,
                             ItemTransforms.TransformType pTransformType,
                             PoseStack pPoseStack, MultiBufferSource pBuffer,
                             int pPackedLight, int pPackedOverlay) {
        Item item = pStack.getItem();
        if (item == ModItems.PISTON_SHIELD.get())
        {
            Material material = new Material(new ResourceLocation("textures/atlas/blocks.png"), new ResourceLocation("entity/shield_base"));
            VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(pBuffer, this.knockbackShield.renderType(material.atlasLocation()), true, pStack.hasFoil()));

            this.knockbackShield.renderToBuffer(pPoseStack,vertexconsumer,pPackedLight,pPackedOverlay, 1,1,1,1);
        }
    }
}
