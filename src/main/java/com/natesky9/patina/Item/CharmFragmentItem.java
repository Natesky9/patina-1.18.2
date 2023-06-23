package com.natesky9.patina.Item;

import com.natesky9.patina.Patina;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class CharmFragmentItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final ResourceLocation fragment1 = new ResourceLocation(Patina.MODID,"bee_fragment_1");
    private static final ResourceLocation fragment2 = new ResourceLocation(Patina.MODID,"bee_fragment_2");
    private static final ResourceLocation fragment3 = new ResourceLocation(Patina.MODID,"bee_fragment_3");
    private static final ResourceLocation fragment4 = new ResourceLocation(Patina.MODID,"bee_fragment_4");

    public CharmFragmentItem(Component p_266834_, Component p_267043_, Component p_267048_, Component p_267278_, Component p_267090_, List<ResourceLocation> p_266755_, List<ResourceLocation> p_267060_) {
        super(p_266834_, p_267043_, p_267048_, p_267278_, p_267090_, p_266755_, p_267060_);
    }
    public static CharmFragmentItem makeFragment()
    {
        Component component1 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_half_a"))).withStyle(DESCRIPTION_FORMAT);
        Component component2 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_half_b"))).withStyle(DESCRIPTION_FORMAT);
        Component component3 = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(Patina.MODID,"fragment_combine"))).withStyle(TITLE_FORMAT);
        Component component4 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_combine"))).withStyle(TITLE_FORMAT);
        Component component5 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_combine"))).withStyle(TITLE_FORMAT);

        return new CharmFragmentItem(component1,component2,component3,component4,component5,createList(),createList());
    }
    private static List<ResourceLocation> createList() {
        return List.of(fragment1,fragment2,fragment3,fragment4);
    }
}
