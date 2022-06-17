package com.natesky9.patina.block.CauldronSmoker;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineCauldronSmokerScreen extends AbstractContainerScreen<MachineCauldronSmokerMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MOD_ID,"textures/gui/machine_cauldron_smoker_gui.png");

    public MachineCauldronSmokerScreen(MachineCauldronSmokerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        if (menu.isCrafting())
        {
            //204,0 52*34
            int progress = menu.getProgress();
            this.blit(pPoseStack, x+62,y+23+progress,204,progress,52,34-progress);
            this.blit(pPoseStack, x+63, y+59,176,0,14,14);
            this.blit(pPoseStack, x+100, y+59,176,0,14,14);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
    }
}
