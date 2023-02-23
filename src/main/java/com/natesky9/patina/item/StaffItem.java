package com.natesky9.patina.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class StaffItem extends Item {
    public StaffItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getName(ItemStack pStack) {
        return super.getName(pStack);
    }

    public static int getWoodColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("wood color");
    }
    public static int getOrnamentColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("ornament color");
    }
    public static int getOrbColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("orb color");
    }
}
