package com.natesky9.patina;

import com.natesky9.patina.init.ModItems;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class PatinaArchery {
    //TODO remove this in favor of the recipe json
    public static final Predicate<ItemStack> BOLTS = (item) ->
            item.is(ModItems.BOLTS.get()) ||
            item.is(ModItems.TIPPED_BOLTS.get()) ||
            item.is(ModItems.ENCHANTED_BOLTS.get());
}
