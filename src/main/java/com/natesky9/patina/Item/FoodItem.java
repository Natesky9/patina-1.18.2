package com.natesky9.patina.Item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class FoodItem extends Item {
    public FoodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        CompoundTag tag = stack.getOrCreateTag();
        int hunger = tag.getInt("hunger");
        float saturation = tag.getFloat("saturation");
        return Foods.APPLE;
    }
}
