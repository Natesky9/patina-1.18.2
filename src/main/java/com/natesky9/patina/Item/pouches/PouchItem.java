package com.natesky9.patina.Item.pouches;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public abstract class PouchItem extends Item {
    public PouchItem(Properties p_41383_) {
        super(p_41383_);
    }
    abstract boolean canAccept(ItemStack self, ItemStack other);
    boolean isSame(ItemStack self, ItemStack other)
    {
        Item existing = getItem(self);
        return existing == Items.AIR || other.is(existing);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction click, Player player, SlotAccess mouse) {
        if (canAccept(self, other) && isSame(self, other))
        {
            int toMove = Math.min(maxCount()-getCount(self),other.getCount());
            setItem(self,other.getItem());
            other.shrink(toMove);
            addCount(self,toMove);
            return true;
        }
        if (other.isEmpty() && click == ClickAction.SECONDARY)
        {
            int count = Math.min(64, getCount(self));
            addCount(self, -count);
            ItemStack fresh = new ItemStack(getItem(self), count);
            mouse.set(fresh);
            if (getCount(self) == 0)
                self.getOrCreateTag().putString("item", Items.AIR.toString());
            return true;
        }
        return super.overrideOtherStackedOnMe(self, other, slot, click, player, mouse);
    }
    Item getItem(ItemStack stack)
    {
        String string = stack.getOrCreateTag().getString("item");
        ResourceLocation location = new ResourceLocation(stack.getOrCreateTag().getString("item"));
        Item item = BuiltInRegistries.ITEM.get(location);
        return item;
    }
    void setItem(ItemStack stack, Item item)
    {
        String string = BuiltInRegistries.ITEM.getKey(item).toString();
        stack.getOrCreateTag().putString("item", string);
    }
    int getCount(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("count");
    }
    void addCount(ItemStack stack, int count)
    {
        int old = getCount(stack);
        stack.getOrCreateTag().putInt("count", old + count);
    }
    abstract int maxCount();

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCount(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return 13*getCount(stack)/maxCount();
    }
}
