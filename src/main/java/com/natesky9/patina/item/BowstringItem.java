package com.natesky9.patina.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BowstringItem extends Item {
    public BowstringItem(Properties pProperties) {
        super(pProperties);
    }
    public static int getColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("string color");
    }
}
