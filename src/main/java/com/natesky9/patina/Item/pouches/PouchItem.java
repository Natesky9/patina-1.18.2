package com.natesky9.patina.Item.pouches;

import com.natesky9.patina.NBTcomponents.StorageComponent;
import com.natesky9.patina.init.ModDataComponents;
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
        StorageComponent component = self.getOrDefault(ModDataComponents.STORAGE_COMPONENT.get(),StorageComponent.EMPTY);
        if (canAccept(self, other) && isSame(self, other))
        {
            int toMove = Math.min(maxCount()-getCount(self),other.getCount());
            if (click == ClickAction.SECONDARY && toMove > 1)
                toMove = 1;
            //setItem(self,other.getItem());
            addCount(self,other.split(toMove));
            return true;
        }
        if (other.isEmpty() && click == ClickAction.SECONDARY)
        {//right-clicking with empty mouse
            int count = Math.min(64, component.getCount());
            ItemStack fresh = new ItemStack(subCount(self,count),count);
            mouse.set(fresh);
            if (getCount(self) == 0)
                self.set(ModDataComponents.STORAGE_COMPONENT.get(),StorageComponent.EMPTY);
                //self.getOrCreateTag().putString("item", Items.AIR.toString());
            return true;
        }
        return super.overrideOtherStackedOnMe(self, other, slot, click, player, mouse);
    }
    Item getItem(ItemStack stack)
    {
        StorageComponent stored = stack.getOrDefault(ModDataComponents.STORAGE_COMPONENT.get(),StorageComponent.EMPTY);
        if (stored.isEmpty()) return Items.AIR;
        Item item = stored.getItem();
        return item;
    }
    int getCount(ItemStack stack)
    {
        StorageComponent component = stack.getOrDefault(ModDataComponents.STORAGE_COMPONENT.get(),StorageComponent.EMPTY);
        return component.getCount();
    }
    void addCount(ItemStack pouch, ItemStack item)
    {
        if (item.isEmpty()) return;
        int old = getCount(pouch);
        pouch.set(ModDataComponents.STORAGE_COMPONENT.get(),new StorageComponent(item.getItemHolder(),old+item.getCount()));
        //stack.getOrCreateTag().putInt("count", old + count);
    }
    Item subCount(ItemStack stack)
    {
        return subCount(stack,1);
        //int count = Math.min(64, getCount(stack));
        //ItemStack taken = stack.getOrDefault(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.EMPTY).getItems().getFirst();
        //stack.set(DataComponents.DAMAGE,stack.get(DataComponents.DAMAGE)-1);
        //taken.shrink(count);
        //stack.set(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.of(taken));
        //return new ItemStack(taken.getItem(),count);
    }
    Item subCount(ItemStack stack, int amount)
    {
        StorageComponent component = stack.getOrDefault(ModDataComponents.STORAGE_COMPONENT.get(),StorageComponent.EMPTY);
        //exit early if empty
        if (component.isEmpty()) return Items.AIR;
        Item taken = component.getItem();
        int count = component.getCount();
        stack.set(ModDataComponents.STORAGE_COMPONENT.get(),new StorageComponent(taken.builtInRegistryHolder(),Math.max(0,count-amount)));
        return taken;
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
