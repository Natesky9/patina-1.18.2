package com.natesky9.patina.item.PowderPouch;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PowderPouchScreen extends AbstractContainerScreen<PowderPouchMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/gui/dust_bag.png");
    public PowderPouchScreen(PowderPouchMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        this.blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);

        //draw the item and text
        CompoundTag tag = menu.selected.getOrCreateTag();
        Item getItem = Registry.ITEM.get(new ResourceLocation(tag.getString("id")));
        ItemStack stack = new ItemStack(getItem);

        if (!stack.isEmpty())
        {
            this.itemRenderer.renderAndDecorateItem(stack, x+80,y+35);
            drawCenteredString(pPoseStack, this.font, "[" + tag.getInt("count") + "]", x+88,y+25, 14737632);
        }

    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack,pMouseX,pMouseY,pPartialTick);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
    }
}
