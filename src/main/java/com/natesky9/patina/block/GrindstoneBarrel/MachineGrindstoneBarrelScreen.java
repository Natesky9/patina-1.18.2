package com.natesky9.patina.block.GrindstoneBarrel;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineGrindstoneBarrelScreen extends AbstractContainerScreen<MachineGrindstoneBarrelMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/gui/machine_grindstone_barrel_gui.png");

    public MachineGrindstoneBarrelScreen(MachineGrindstoneBarrelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        boolean crafting = menu.isCrafting();
        float progress = menu.getProgress();
        float fluid = menu.getFluid();

        if (crafting)
        {
            //draw the progress arrow
            int height = (int)(22*progress);
            this.blit(pPoseStack,x+126,y+30,210,0,16,height);
        }
        //draw the grindstone animation
        int scroll = (int)(progress*5*28);
        int drips = (int)(progress*6*6);
        this.blit(pPoseStack,x+94,y+23,176,scroll,12,28);
        this.blit(pPoseStack,x+92,y+53,188, 36-drips, 16,6);
        //draw the bar
        int bar = (int)(fluid*41);
        this.blit(pPoseStack,x+57,y+33+41-bar,204,41-bar,6, bar);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
    }
}
