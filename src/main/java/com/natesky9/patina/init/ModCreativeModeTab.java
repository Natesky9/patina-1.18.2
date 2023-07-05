package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static CreativeModeTab PATINA_TAB;
    public static CreativeModeTab SALT_TAB;
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
        if (event.getTab() == ModCreativeModeTab.SALT_TAB)
        {
            //accept salts for each effect
            for (MobEffect effect:BuiltInRegistries.MOB_EFFECT)
            {
                ItemStack stack = new ItemStack(ModItems.POTION_SALT.get());
                PotionUtils.setPotion(stack,new Potion(new MobEffectInstance(effect)));
                event.accept(stack);
            }
        }
    }
    //
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event)
    {
        PATINA_TAB = event.registerCreativeModeTab(new ResourceLocation(Patina.MODID,"patina_tab"),
                builder -> builder.icon(() -> new ItemStack(Items.COPPER_INGOT))
                        .title(Component.translatable("creativemodetab.patina_tab")));
        SALT_TAB = event.registerCreativeModeTab(new ResourceLocation(Patina.MODID, "salt_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.POTION_SALT.get()))
                        .title(Component.translatable("creativemodetab.salt_tab")));
    }
}
