package com.natesky9.patina.item;

import net.minecraft.client.Game;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class CopperItem extends ArmorItem {
    private static int rustMax = 20000;

    public CopperItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        int rust = pStack.getOrCreateTag().getInt("rust");
        pTooltipComponents.add(new TextComponent("Rust: " + rust + "/" + rustMax));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        int rust = pStack.getOrCreateTag().getInt("rust");
        rust++;
        if (Screen.hasShiftDown()) rust += 100;
        rust = Math.min(rust,rustMax);
        pStack.getOrCreateTag().putInt("rust",rust);
    }

    public static int getRust(ItemStack item) {
        return item.getOrCreateTag().getInt("rust");
    }

    public static int getRustColor(ItemStack item)
    {
        float value = (20+(float)getRust(item)/(float)rustMax*100)/255;
        //System.out.println("rust: " + value);
        int color = Color.HSBtoRGB(value,1,1);
        System.out.println("color: " + color);
        return color;
        //return makeRustColor(getRustValue(item)).getRGB();
    }

}
