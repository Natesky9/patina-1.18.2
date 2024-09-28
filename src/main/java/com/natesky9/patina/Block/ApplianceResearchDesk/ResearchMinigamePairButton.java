package com.natesky9.patina.Block.ApplianceResearchDesk;

import com.natesky9.patina.networking.ModPackets;
import com.natesky9.patina.networking.PacketResearchAdvance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

public class ResearchMinigamePairButton extends AbstractButton {
    ResearchMinigame screen;
    int value;
    boolean visible;
    public ResearchMinigamePairButton(int p_93365_, int p_93366_,ResearchMinigame screen) {
        super(p_93365_, p_93366_, 32,32, Component.empty());
        this.screen = screen;
        this.visible = false;
        this.active = true;
        this.setMessage(Component.literal("?"));
    }

    @Override
    public void onPress() {
        SoundManager manager = Minecraft.getInstance().getSoundManager();
        for (AbstractButton old:screen.list)
        {
            if (old != screen.button)
                old.setMessage(Component.literal("?"));
        }
        this.visible = true;
        this.setMessage(Component.literal(String.valueOf(value)));
        ResearchMinigamePairButton button = (ResearchMinigamePairButton) screen.button;
        if (screen.button == null)
        {
            screen.button = this;
            return;
        }
        int other = ((ResearchMinigamePairButton) screen.button).value;
        if (value == other)
        {
            this.active = false;
            button.active = false;
            boolean done = checkCompleted();
            screen.button = null;
            if (done)
            {
                manager.play(SimpleSoundInstance.forUI(SoundEvents.PLAYER_LEVELUP,.5f));
                ModPackets.sendToServer(new PacketResearchAdvance(screen.topic.holder().id(), screen.node.holder().id()));
                screen.onClose();
                return;
            }
            manager.play(SimpleSoundInstance.forUI(SoundEvents.EXPERIENCE_ORB_PICKUP,(float)Math.random()));
        }
        else
        {
            this.visible = false;
            button.visible = false;
            screen.button = null;
            setFocused(false);
            manager.play(SimpleSoundInstance.forUI(SoundEvents.COMPOSTER_READY,1f));
        }

    }
    boolean checkCompleted()
    {
        for (AbstractButton button: screen.list)
        {//false if a button is still active
            if (button.active)
                return false;
        }
        //true if all gone
        return true;
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(SimpleSoundInstance.forUI(SoundEvents.VILLAGER_WORK_CARTOGRAPHER,1f));
    }

    @Override
    public boolean isHoveredOrFocused() {
        return super.isHoveredOrFocused() || screen.button == this;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}
