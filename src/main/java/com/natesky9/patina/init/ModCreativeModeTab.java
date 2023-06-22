package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static CreativeModeTab PATINA_TAB;
    //
    public static void addItem(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            event.accept(ModItems.BEE_SHIELD);
            event.accept(ModItems.BEE_SWORD);
            event.accept(ModItems.PIG_CROSSBOW);
            event.accept(ModItems.PIG_SWORD);
        }
        if (event.getTab() == ModCreativeModeTab.PATINA_TAB)
        {
            for (RegistryObject<Item> item:ModItems.ITEMS.getEntries())
            {
                event.accept(item);
            }
        }
    }
    //
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event)
    {
        PATINA_TAB = event.registerCreativeModeTab(new ResourceLocation(Patina.MODID,"patina_tab"),
                builder -> builder.icon(() -> new ItemStack(Items.COPPER_INGOT))
                        .title(Component.translatable("creativemodetab.patina_tab")));
    }
}
