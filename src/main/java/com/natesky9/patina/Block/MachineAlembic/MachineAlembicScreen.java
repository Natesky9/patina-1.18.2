package com.natesky9.patina.Block.MachineAlembic;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class MachineAlembicScreen extends AbstractContainerScreen<MachineAlembicMenu> implements net.minecraft.world.inventory.ContainerListener {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MODID, "textures/gui/machine_cauldron_brewing_gui.png");
    public MachineAlembicScreen(MachineAlembicMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.menu.addSlotListener(this);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;
        renderTooltip(graphics, mouseX, mouseY);
        int yield = menu.data.get(4);
        int connected = menu.data.get(0);
        graphics.drawString(font,"reagent: " + yield,x+80,y+13, Color.WHITE.getRGB());
        graphics.drawString(font, "neightbors: " + connected,x,y,Color.WHITE.getRGB());
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        graphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void slotChanged(AbstractContainerMenu p_39315_, int p_39316_, ItemStack p_39317_) {

    }

    @Override
    public void dataChanged(AbstractContainerMenu p_150524_, int p_150525_, int p_150526_) {
        menu.data.set(p_150525_, p_150526_);
    }
}
