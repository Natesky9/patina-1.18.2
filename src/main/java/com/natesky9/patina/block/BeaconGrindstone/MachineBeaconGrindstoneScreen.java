package com.natesky9.patina.block.BeaconGrindstone;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineBeaconGrindstoneScreen extends AbstractContainerScreen<MachineBeaconGrindstoneMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/gui/machine_beacon_grindstone_gui.png");

    public MachineBeaconGrindstoneScreen(MachineBeaconGrindstoneMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;
        //draw the menu
        this.blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);
        //draw the beam
        if (menu.isCrafting())
            {
            this.blit(pPoseStack,x+83,y+40,176,31,10,16);
            //draw the progress arrow
            int arrowSize = (int) (menu.getProgress()*24);
            this.blit(pPoseStack,x+97,y+24,176,14,arrowSize,16);
            }
        int fuelSize = (int) (menu.getFuel()*31);
        this.blit(pPoseStack,x+102,y+41+31-fuelSize,176,47+31-fuelSize,5,fuelSize);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
    }
}
