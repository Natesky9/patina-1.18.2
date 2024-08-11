package com.natesky9.patina.Item.pouches;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;

public abstract class PouchItem extends Item {
    //TODO: create a custom datacomponent to deal with this
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
            //setItem(self,other.getItem());
            other.shrink(toMove);
            addCount(self,other);
            return true;
        }
        if (other.isEmpty() && click == ClickAction.SECONDARY)
        {
            ItemStack fresh = subCount(self);
            mouse.set(fresh);
            if (getCount(self) == 0)
                self.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(ItemStack.EMPTY));
                //self.getOrCreateTag().putString("item", Items.AIR.toString());
            return true;
        }
        return super.overrideOtherStackedOnMe(self, other, slot, click, player, mouse);
    }
    Item getItem(ItemStack stack)
    {
        ItemStack item = stack.get(DataComponents.CHARGED_PROJECTILES).getItems().get(0);
        if (item == ItemStack.EMPTY) return Items.AIR;
        //String string = stack.getOrCreateTag().getString("item");
        //ResourceLocation location = new ResourceLocation(stack.getOrCreateTag().getString("item"));
        //Item item = BuiltInRegistries.ITEM.get(location);
        return item.getItem();
    }
    //void setItem(ItemStack stack, Item item)
    //{
    //    stack.set(DataComponents.CHARGED_PROJECTILES,)
    //    //String string = BuiltInRegistries.ITEM.getKey(item).toString();
    //    //stack.getOrCreateTag().putString("item", string);
    //}
    int getCount(ItemStack stack)
    {
        if (!stack.getComponents().has(DataComponents.CHARGED_PROJECTILES)) return 0;
        ItemStack item =  stack.get(DataComponents.CHARGED_PROJECTILES).getItems().get(0);
        if (item == ItemStack.EMPTY) return 0;
        return item.getCount();
    }
    void addCount(ItemStack stack, ItemStack item)
    {
        int old = getCount(stack);
        stack.set(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.of(
                new ItemStack(item.getItem(),old+item.getCount())));
        //stack.getOrCreateTag().putInt("count", old + count);
    }
    ItemStack subCount(ItemStack stack)
    {
        int count = Math.min(64, getCount(stack));
        ItemStack taken = stack.get(DataComponents.CHARGED_PROJECTILES).getItems().get(0);
        taken.shrink(count);
        stack.set(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.of(taken));
        return new ItemStack(taken.getItem(),count);
    }
    ItemStack subCount(ItemStack stack, int count)
    {
        ItemStack taken = stack.get(DataComponents.CHARGED_PROJECTILES).getItems().get(0);
        taken.shrink(count);
        stack.set(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.of(taken));
        return new ItemStack(taken.getItem(),count);
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
