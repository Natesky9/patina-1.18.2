package com.natesky9.patina.Block.MachineFoundry;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineFoundryScreen extends AbstractContainerScreen<MachineFoundryMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MODID, "textures/gui/foundry_gui.png");
    public MachineFoundryScreen(MachineFoundryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack,pMouseX,pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);
        if (menu.isCrafting())
        {
            int progress = menu.getProgress();
            blit(pPoseStack,x+79,y+35,176,14,progress+1,17);
        }
    }
}
