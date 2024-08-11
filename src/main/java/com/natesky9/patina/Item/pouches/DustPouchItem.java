package com.natesky9.patina.Item.pouches;

import com.natesky9.patina.init.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.common.Tags;

public class DustPouchItem extends PouchItem{
    public DustPouchItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    boolean canAccept(ItemStack self, ItemStack other) {
        return other.is(Tags.Items.DUSTS);
    }

    @Override
    int maxCount() {
        return 640;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (getItem(stack) == (Items.GLOWSTONE_DUST)) return Mth.color(1,1,0);
        if (getItem(stack) == (Items.REDSTONE)) return Mth.color(1,0,0);
        if (getItem(stack) == (Items.SUGAR)) return Mth.color(1,1,1);
        if (getItem(stack) == (ModItems.POTION_SALT.get()))
            return PotionContents.getColor(stack.get(DataComponents.POTION_CONTENTS).potion().get());
        return Mth.color(0,0,1);
    }
}
