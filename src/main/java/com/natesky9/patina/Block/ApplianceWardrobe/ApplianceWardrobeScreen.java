package com.natesky9.patina.Block.ApplianceWardrobe;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ApplianceWardrobeScreen extends AbstractContainerScreen<ApplianceWardrobeMenu> {
    public ApplianceWardrobeScreen(ApplianceWardrobeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

    }
}
