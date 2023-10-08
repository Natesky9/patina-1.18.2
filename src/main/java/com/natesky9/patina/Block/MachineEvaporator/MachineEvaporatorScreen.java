package com.natesky9.patina.Block.MachineEvaporator;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineEvaporatorScreen extends AbstractContainerScreen<MachineEvaporatorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MODID,"textures/gui/machine_evaporator.png");
    public MachineEvaporatorScreen(MachineEvaporatorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(graphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(graphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        graphics.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);
        boolean crafting = menu.isCrafting();
        if (crafting)
        {
            //204,0 52*34
            int progress = menu.getProgress();
            graphics.blit(TEXTURE, x+62,y+23+progress,204,progress,52,34-progress);
            graphics.blit(TEXTURE, x+63, y+59,176,0,14,14);
            graphics.blit(TEXTURE, x+100, y+59,176,0,14,14);
        }
    }
}
