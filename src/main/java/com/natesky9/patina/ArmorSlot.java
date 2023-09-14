package com.natesky9.patina;

import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class ArmorSlot extends Slot {
    int slot;
    public ArmorSlot(Container pContainer, int pSlot, int pX, int pY,int armorSlot) {
        super(pContainer, pSlot, pX, pY);
        slot = armorSlot;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        if (pStack.getItem() instanceof ArmorItem armor)
            return armor.getType().ordinal() == slot;
        //allow for helmet items
        return slot == ArmorItem.Type.HELMET.ordinal();
    }

}
