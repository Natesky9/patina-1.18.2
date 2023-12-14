package com.natesky9.patina.Item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.jetbrains.annotations.Nullable;

public class FoodItem extends Item {
    public FoodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        FoodProperties food = getFoodProperties();
        if (food == null) return Foods.DRIED_KELP;
        Potion potion = PotionUtils.getPotion(stack);
        FoodProperties.Builder custom = new FoodProperties.Builder().nutrition(food.getNutrition())
                .saturationMod(food.getSaturationModifier());
       for (MobEffectInstance instance: potion.getEffects())
       {
           custom.effect(() -> instance,1f/potion.getEffects().size());
       }
       return custom.build();
    }
}
