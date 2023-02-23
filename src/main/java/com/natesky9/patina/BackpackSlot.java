package com.natesky9.patina;

import com.natesky9.patina.init.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BackpackSlot extends Slot {
    ItemStack bag;
    public BackpackSlot(Container pContainer, int pIndex, int pX, int pY, ItemStack stack) {
        super(pContainer, pIndex, pX, pY);
        bag = stack;
    }
    @Override
    public int getMaxStackSize() {
        return 1024;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return pStack.is(ModItems.BOLTS.get())
                || pStack.is(ModItems.TIPPED_BOLTS.get())
                || pStack.is(ModItems.ENCHANTED_BOLTS.get());
    }

    @Override
    public ItemStack safeInsert(ItemStack p_150660_) {
        ItemStack stack = super.safeInsert(p_150660_);
        bag.getOrCreateTag().putInt("True Count",getItem().getCount());
        return stack;
    }

    @Override
    public ItemStack safeTake(int p_150648_, int p_150649_, Player p_150650_) {
        ItemStack stack = super.safeTake(p_150648_, p_150649_, p_150650_);
        bag.getOrCreateTag().putInt("True Count",getItem().getCount());
        return stack;
    }
}
