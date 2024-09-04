package com.natesky9.patina.Block.ApplianceResearchDesk;

import com.natesky9.patina.ResearchWidget;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementNode;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ResearchScreen extends Screen {
    private static ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/recipe_book.png");
    Advancement advancement;
    protected ResearchScreen(AdvancementNode node) {
        super(node.advancement().display().get().getTitle());
        advancement = node.advancement();

        AdvancementRequirements advancement = node.advancement().requirements();

        //AdvancementRequirements requirements = node.advancement().requirements();

    }

    @Override
    public void render(GuiGraphics graphics, int p_281550_, int p_282878_, float p_282465_) {
        int imageWidth = 148;
        int imageHeight = 167;
        int x = (width/2)-imageWidth/2;
        int y = (height/2)-(imageHeight+17)/2;
        //render background
        //TODO place the background in the actual background method
        this.renderBlurredBackground(p_282465_);
        graphics.blit(TEXTURE, x, y, 0, 0, 256, 256);
        //super.render(graphics, p_281550_, p_282878_, p_282465_);
        for (Renderable renderable : this.renderables) {
            renderable.render(graphics, p_281550_, p_282878_, p_282465_);
        }

        ItemStack display = advancement.display().get().getIcon();
        graphics.renderFakeItem(display, width/2-8, y +16);
    }

    @Override
    public boolean mouseClicked(double p_94695_, double p_94696_, int p_94697_) {
        return super.mouseClicked(p_94695_, p_94696_, p_94697_);
    }

    @Override
    protected void init() {
        AdvancementRequirements intermediate = advancement.requirements();
        AbstractWidget widget;
        Component component;
        int i = 0;
        ClientAdvancements client = minecraft.player.connection.getAdvancements();
        for (List<String> requirements: intermediate.requirements())
        {
            for (String string: requirements)
            {
                AdvancementHolder required = client.get(ResourceLocation.parse(string));
                if (required == null)
                    continue;
                //only add widgets for advancements that exist
                int imageWidth = 148;
                int imageHeight = 167;
                int x = width/2 - imageWidth/2 + 26*i + 14;
                int y = height/2 +imageHeight/2 - 44;
                component = Component.literal(string);
                widget = new ResearchWidget(x,y,component,required.value(),client);
                addRenderableWidget(widget);
                i++;
            }
        }


        //this.widget = Button.builder(Component.empty(),press -> System.out.println(node))
        //        .pos(100,100)
        //        .build();
        AbstractWidget back = Button.builder(CommonComponents.GUI_BACK, press ->
                {
                    this.onClose();//replace with poplayer?
                })
                .pos(140,200)
                .build();
        addRenderableWidget(back);
    }
}
