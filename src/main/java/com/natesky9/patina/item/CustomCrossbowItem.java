package com.natesky9.patina.item;

import com.natesky9.patina.PatinaArchery;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class CustomCrossbowItem extends CrossbowItem {
    public CustomCrossbowItem(Properties p_40850_) {
        super(p_40850_);
    }
    //

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return PatinaArchery.BOLTS;
    }

    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return PatinaArchery.BOLTS;
    }
}
