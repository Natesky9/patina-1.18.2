package com.natesky9.patina.Block.Benchmark;

import com.mojang.blaze3d.systems.RenderSystem;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ApplianceBenchmarkScreen extends AbstractContainerScreen<ApplianceBenchmarkMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Patina.MODID, "textures/gui/appliance_benchmark.png");
    private static final Vector3f ARMOR_STAND_TRANSLATION = new Vector3f();
    private static final Quaternionf ARMOR_STAND_ANGLE = new Quaternionf().rotationXYZ(0.43633232F, 0.0F, (float) Math.PI);
    int xMouse;
    int yMouse;
    final int grey = 4210752;
    final int green = 65280;

    int norm;
    int proj;
    int fire;
    int fall;
    int blast;
    int magic;

    public ApplianceBenchmarkScreen(ApplianceBenchmarkMenu pMenu, Inventory pInventory, Component pTitle) {
        super(pMenu, pInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        leftPos -= 80;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0, TEXTURE);

        graphics.blit(TEXTURE,leftPos,topPos,0,0,imageWidth,imageHeight);

        norm  = Math.min(menu.data.get(0),80);
        proj  = Math.min(menu.data.get(1),80);
        fall  = Math.min(menu.data.get(2),80);
        fire  = Math.min(menu.data.get(3),80);
        blast = Math.min(menu.data.get(4),80);
        magic = Math.min(menu.data.get(5),80);

        graphics.drawString(this.font,"Melee " + norm + "%",leftPos+50,topPos+16,
                norm >= 80 ? green:grey,false);
        graphics.drawString(this.font,"Ranged " + proj + "%",leftPos+50,topPos+30,
                proj >= 80 ? green:grey,false);
        graphics.drawString(this.font,"Magic " + magic + "%",leftPos+50,topPos+44,
                magic >= 80 ? green:grey,false);
        graphics.drawString(this.font,"Fire " + fire + "%",leftPos+120,topPos+16,
                fire >= 80 ? green:grey,false);
        graphics.drawString(this.font,"Blast " + blast + "%",leftPos+120,topPos+30,
                blast >= 80 ? green:grey,false);
        graphics.drawString(this.font,"Fall " + fall + "%",leftPos+116,topPos+44,
                fall >= 80 ? green:grey,false);
        Quaternionf angle = new Quaternionf().rotationXYZ(.2f,.3f+(float)Math.PI,(float)Math.PI);
        if (menu.entity != null)
            InventoryScreen.renderEntityInInventory(graphics, (float)(leftPos + 25), (float)(topPos + 70), 32.0F, ARMOR_STAND_TRANSLATION, angle, null, menu.entity);

    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {
        super.renderLabels(p_281635_, p_282681_, p_283686_);
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(graphics, pMouseX, pMouseY, pPartialTick);
        xMouse = pMouseX;
        yMouse = pMouseY;
        renderTooltip(graphics,leftPos+imageWidth,imageHeight/2);
    }

    @Override
    protected void renderSlot(GuiGraphics p_281607_, Slot p_282613_) {
        if (p_282613_.index >= 4)
            super.renderSlot(p_281607_, p_282613_);
    }
}
