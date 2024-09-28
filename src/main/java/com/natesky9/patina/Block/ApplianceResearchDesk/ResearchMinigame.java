package com.natesky9.patina.Block.ApplianceResearchDesk;

import com.natesky9.patina.networking.ModPackets;
import com.natesky9.patina.networking.PacketResearchAdvance;
import net.minecraft.advancements.AdvancementNode;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResearchMinigame extends Screen {
    AdvancementNode topic;
    AdvancementNode node;
    AbstractButton button;
    List<AbstractButton> list = new ArrayList<>();
    protected ResearchMinigame(AdvancementNode topic, AdvancementNode node) {
        super(Component.literal("Minigame"));
        this.topic = topic;
        this.node = node;
    }

    @Override
    protected void init() {
        button = null;
        //generate the match 2
        ResearchMinigamePairButton pairButton;
        for (int i = 0; i < 16; i++)
        {
            pairButton = new ResearchMinigamePairButton(0,0,this);
            pairButton.value = (i/2)+1;
            list.add(pairButton);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 16; i++)
        {
            pairButton = (ResearchMinigamePairButton) list.get(i);
            pairButton.setX((i%4) * 32+60);
            pairButton.setY((i/4) * 32+48);
            addRenderableWidget(pairButton);
        }


        AbstractWidget back = Button.builder(CommonComponents.GUI_BACK, press ->
                {
                    this.onClose();//replace with poplayer?
                })
                .pos(140,200).build();
        addRenderableWidget(back);
        AbstractWidget finish = Button.builder(CommonComponents.GUI_CONTINUE, press ->
        {
            ModPackets.sendToServer(new PacketResearchAdvance(topic.holder().id(), node.holder().id()));
            onClose();
        }
        ).build();
        addRenderableWidget(finish);
    }

    @Override
    public boolean mouseClicked(double p_94695_, double p_94696_, int p_94697_) {
        return super.mouseClicked(p_94695_, p_94696_, p_94697_);
    }

    @Override
    public void onClose() {
        super.onClose();
        //ModPackets.sendToServer(new PacketResearchAdvance(node.holder().id(), index));
    }
}
