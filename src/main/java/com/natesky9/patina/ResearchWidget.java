package com.natesky9.patina;

import net.minecraft.advancements.Advancement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ResearchWidget extends AbstractButton {
    private final Advancement advancement;
    private final ClientAdvancements client;
    private final ItemStack stack;
    public ResearchWidget(int p_93365_, int p_93366_, Component p_93369_, Advancement advancement, ClientAdvancements client) {
        super(p_93365_, p_93366_, 22, 22, p_93369_);
        this.advancement = advancement;
        this.client = client;
        this.stack = advancement.display().get().getIcon();
    }

    @Override
    public void onPress() {
        System.out.println("pressed");
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.renderWidget(graphics, mouseX, mouseY, partialTick);
        graphics.renderFakeItem(stack, getX()+3, getY()+3);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput p_259858_) {

    }
}
