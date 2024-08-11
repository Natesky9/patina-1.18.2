package com.natesky9.patina.Block.MachineTextiler;

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

public class MachineTextilerScreen extends AbstractContainerScreen<MachineTextilerMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Patina.MODID,"textures/gui/machine_textiler_gui.png");
    public final RecipeBookComponent component;
    private boolean widthTooNarrow;
    public MachineTextilerScreen(MachineTextilerMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
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
    protected void renderBg(GuiGraphics graphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = this.leftPos;
        int y = this.topPos;

        graphics.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);
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
}
