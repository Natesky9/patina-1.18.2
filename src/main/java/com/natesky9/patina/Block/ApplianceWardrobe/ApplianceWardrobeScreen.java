package com.natesky9.patina.Block.ApplianceWardrobe;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import static net.minecraft.client.gui.screens.inventory.InventoryScreen.renderEntityInInventoryFollowsMouse;

public class ApplianceWardrobeScreen extends AbstractContainerScreen<ApplianceWardrobeMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MODID,"textures/gui/appliance_wardrobe.png");
    int xMouse;
    int yMouse;
    public ApplianceWardrobeScreen(ApplianceWardrobeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(graphics, pMouseX, pMouseY, pPartialTick);
        xMouse = pMouseX;
        yMouse = pMouseY;
        renderTooltip(graphics, getGuiLeft() + width, getGuiTop());
    }

    @Override
    protected boolean hasClickedOutside(double pMouseX, double pMouseY, int pGuiLeft, int pGuiTop, int pMouseButton) {
        boolean flag = super.hasClickedOutside(pMouseX,pMouseY,pGuiLeft,pGuiTop,pMouseButton);
        if (flag) {
            Player player = this.minecraft.player;
            if (player != null)
                player.closeContainer();
        }
        return flag;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        graphics.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);
        if (this.minecraft.player != null)
            renderEntityInInventoryFollowsMouse(graphics, x + 51, y + 75, 30,
                (x + 51) - this.xMouse, (y + 75 - 50) - this.yMouse,
                0,0,0, this.minecraft.player);

    }
}
