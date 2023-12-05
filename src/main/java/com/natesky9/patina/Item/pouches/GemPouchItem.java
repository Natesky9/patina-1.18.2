package com.natesky9.patina.Item.pouches;

import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class GemPouchItem extends PouchItem{
    public GemPouchItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    boolean canAccept(ItemStack self, ItemStack other) {
        return other.is(Tags.Items.GEMS);
    }

    @Override
    int maxCount() {
        return 640;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        Item item = getItem(stack);
        if (item == (Items.DIAMOND)) return Mth.color(0,1,1);
        if (item == (Items.EMERALD)) return Mth.color(0,1,0);
        if (item == (Items.AMETHYST_SHARD)) return Mth.color(1,1,0);
        if (item == (Items.LAPIS_LAZULI)) return Mth.color(0,0,1);
        return Mth.color(1,1,1);
    }
}
