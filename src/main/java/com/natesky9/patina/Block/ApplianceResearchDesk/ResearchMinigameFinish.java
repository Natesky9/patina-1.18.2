package com.natesky9.patina.Block.ApplianceResearchDesk;

import com.natesky9.patina.networking.ModPackets;
import com.natesky9.patina.networking.PacketResearchAdvance;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.CommonComponents;

public class ResearchMinigameFinish extends AbstractButton {
    ResearchMinigame screen;
    public ResearchMinigameFinish(int pX, int pY, ResearchMinigame screen) {
        super(pX, pY, 128, 20, CommonComponents.GUI_CONTINUE);
        this.screen = screen;
    }

    @Override
    public void onPress() {
        ModPackets.sendToServer(new PacketResearchAdvance(screen.topic.holder().id(), screen.node.holder().id()));
        screen.onClose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}
