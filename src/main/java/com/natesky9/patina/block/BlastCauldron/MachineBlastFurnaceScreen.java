package com.natesky9.patina.block.BlastCauldron;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineBlastFurnaceScreen extends AbstractContainerScreen<MachineBlastCauldronMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/gui/machine_blast_cauldron_gui.png");

    public MachineBlastFurnaceScreen(MachineBlastCauldronMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        this.blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);
        if (menu.isCrafting())
        {
            this.blit(pPoseStack,x+73,y+19,176,0,menu.getProgress(),8);
            //draw the lit flame
            this.blit(pPoseStack,x+78,y+58,202,0,14,14);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
    }
}
