package com.natesky9.patina.Block.ApplianceResearchDesk;

import com.natesky9.patina.networking.ModPackets;
import com.natesky9.patina.networking.PacketResearchCreativeGrant;
import net.minecraft.advancements.AdvancementNode;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSeenAdvancementsPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.Iterator;

public class ApplianceResearchDeskScreen extends AbstractContainerScreen<ApplianceResearchDeskMenu>{
    private static final Component TITLE = Component.literal("Research");
    private ClientAdvancements advancements;
    public Screen lastScreen;
    private Screen research;
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    private AdvancementsScreen advancementsScreen;

    public ApplianceResearchDeskScreen(ApplianceResearchDeskMenu menu, Inventory inv, Component component) {
        super(menu, inv, TITLE);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        //draw the advancements
        //this.renderInside(mouseX, mouseY, i,j);
        //leftPos = (this.width - 252) / 2;
        //topPos = (this.height - 140) / 2;
        //advancementsScreen.renderBackground(graphics,pMouseX,pMouseY,pPartialTick);
        return;
        //renderInside------------------------------
        //AdvancementTab advancementtab = this.selectedTab;
        //if (advancementtab == null) {
        //    graphics.fill(leftPos + 9, topPos + 18, leftPos + 9 + 234, topPos + 18 + 113, -16777216);
        //    int tex = leftPos + 9 + 117;
        //    graphics.drawCenteredString(this.font, Component.literal("oops"), tex, topPos + 18 + 56 - 9 / 2, -1);
        //    graphics.drawCenteredString(this.font, Component.literal("nothing to see here"), tex, topPos + 18 + 113 - 9, -1);
        //} else {
        //    advancementtab.drawContents(graphics, leftPos + 9, topPos + 18);
        //}
        //
    }

    @Override
    protected void init()
    {
        super.init();
        //
        //imageWidth = 252;
        //imageHeight = 400;
        this.advancements = this.minecraft.player.connection.getAdvancements();
        advancementsScreen = new AdvancementsScreen(this.advancements);
        advancementsScreen.init(minecraft,width,height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        advancementsScreen.render(graphics,mouseX,mouseY,partialTick);
        insideAdvancementScreen(mouseX,mouseY);

        //delete this later

        int i = (this.width - 252) / 2;
        int j = (this.height - 140) / 2;
        boolean inside = (mouseX > i+9 && mouseX < i+234+9 && mouseY > j+18 && mouseY < j+113+18);
        //hover stuff

        int a = Mth.floor(advancementsScreen.selectedTab.scrollX)+i+9;
        int b = Mth.floor(advancementsScreen.selectedTab.scrollY)+j+18;
        if (!insideAdvancementScreen(mouseX, mouseY)) return;
        //we've clicked inside the advancements
        Iterator var9 = advancementsScreen.selectedTab.widgets.values().iterator();
        while(var9.hasNext())
        {
            AdvancementWidget widget = (AdvancementWidget) var9.next();
            if (widget.isMouseOver(a, b, mouseX, mouseY) && minecraft.player.isCreative())
            {
                graphics.drawString(font,"Hold alt to grant",4,4,16777215);
            }
        }
    }
    private boolean insideAdvancementScreen(int mouseX, int mouseY)
    {
        int i = (this.width - 252) / 2;
        int j = (this.height - 140) / 2;
        boolean inside = (mouseX > i+9 && mouseX < i+234+9 && mouseY > j+18 && mouseY < j+113+18);
        return inside;
    }


    @Override
    public void removed() {
        this.advancements.setListener(null);
        ClientPacketListener clientPacketListener = this.minecraft.getConnection();
        if (clientPacketListener != null)
        {
            clientPacketListener.send(ServerboundSeenAdvancementsPacket.closedScreen());
        }
    }

    boolean clickAdvancements(double x, double y)
    {
        int mouseX = (int)x;
        int mouseY = (int)y;
        //i and j are the top left of the screen
        int i = (advancementsScreen.width - 252) / 2;
        int j = (advancementsScreen.height - 140) / 2;

        int a = Mth.floor(advancementsScreen.selectedTab.scrollX)+i+9;
        int b = Mth.floor(advancementsScreen.selectedTab.scrollY)+j+18;
        if (!insideAdvancementScreen(mouseX, mouseY)) return false;
        //we've clicked inside the advancements
        Iterator var9 = advancementsScreen.selectedTab.widgets.values().iterator();
        while(var9.hasNext())
        {
            AdvancementWidget widget = (AdvancementWidget) var9.next();
            if (widget.isMouseOver(a, b,mouseX,mouseY))
            {
                AdvancementNode node = widget.advancementNode;
                if (this.menu.player.isCreative() && hasAltDown())
                {//if creative, grant the advancement
                    //System.out.println(node);
                    System.out.println("granting: " + node.holder().id());
                    ModPackets.sendToServer(new PacketResearchCreativeGrant(node.holder().id()));
                    return true;
                }

                research = new ResearchScreen(node,advancementsScreen);
                research.init(minecraft,width,height);
                ForgeHooksClient.pushGuiLayer(minecraft,research);


                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double x, double y, int button) {
        if (button == 0)
        {
            //change tabs
            //if (clickTabs(x,y)) return super.mouseClicked(x,y,button);
            if (advancementsScreen.mouseClicked(x,y,button)) return super.mouseClicked(x,y,button);
            if (clickAdvancements(x,y)) return super.mouseClicked(x,y,button);
            //click on advancement

        }

        return super.mouseClicked(x, y, button);
    }

    @Override
    public boolean mouseDragged(double p_97752_, double p_97753_, int mouse, double x, double y) {
        return advancementsScreen.mouseDragged(p_97752_,p_97753_,mouse,x,y);
    }

    @Override
    public boolean mouseScrolled(double p_94686_, double p_94687_, double p_301134_, double p_300488_) {
        return false;
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
    }
}
