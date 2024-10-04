package com.natesky9.patina.Item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ColdFoodItem extends FoodItem{
    public ColdFoodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity entity) {
        entity.extinguishFire();
        entity.setTicksFrozen(entity.getTicksFrozen()+10);
        return super.finishUsingItem(p_41409_, p_41410_, entity);
    }
}
