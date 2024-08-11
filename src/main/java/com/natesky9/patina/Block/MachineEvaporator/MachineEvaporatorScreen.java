package com.natesky9.patina.Block.MachineEvaporator;

import com.mojang.blaze3d.systems.RenderSystem;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineEvaporatorScreen extends AbstractContainerScreen<MachineEvaporatorMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Patina.MODID,"textures/gui/machine_evaporator.png");
    public final RecipeBookComponent component;
    private boolean widthTooNarrow;
    public MachineEvaporatorScreen(MachineEvaporatorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        component = new RecipeBookComponent();
    }

    @Override
    protected void init() {
        super.init();

        //have to do all this because of the recipe book
        this.widthTooNarrow = this.width < 379;
        this.component.init(this.width,this.height,this.minecraft,this.widthTooNarrow,this.menu);
        this.leftPos = this.component.updateScreenPosition(this.width,this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos, this.height / 2-60, 20, 18,
                RecipeBookComponent.RECIPE_BUTTON_SPRITES,
                (input) -> {
                    this.component.toggleVisibility();
                    this.leftPos = this.component.updateScreenPosition(this.width, this.imageWidth);
                    input.setPosition(this.leftPos, this.height / 2 - 60);
                }));
        this.titleLabelX = (this.imageWidth - this.font.width(this.title))/2;
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.component.tick();
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        if (this.component.isVisible() && this.widthTooNarrow)
        {
            this.renderBackground(graphics,pMouseX,pMouseY,pPartialTick);
            this.component.render(graphics,pMouseX,pMouseY,pPartialTick);
        }
        else
        {
            super.render(graphics,pMouseX,pMouseY,pPartialTick);
            this.component.render(graphics,pMouseX,pMouseY,pPartialTick);
            //this.component.renderGhostRecipe();
        }
        renderTooltip(graphics,pMouseX,pMouseY);
        this.component.renderTooltip(graphics,this.leftPos,this.topPos,pMouseX,pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = this.leftPos;
        int y = this.topPos;

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
