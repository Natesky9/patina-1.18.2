package com.natesky9.patina.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties TEST_FOOD = new FoodProperties.Builder().nutrition(1).saturationMod(.2f)
            .build();
    public static final FoodProperties BLINK_BROWNIE = new FoodProperties.Builder()
            .nutrition(1).saturationMod(2f).alwaysEat().fast()
            .build();
    public static final FoodProperties CANDY_WARTS = new FoodProperties.Builder()
            .nutrition(1).saturationMod(1).alwaysEat().fast()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),.5F)
            .effect(new MobEffectInstance(MobEffects.HUNGER,200,0),.5F)
            .build();
    public static final FoodProperties HERB = new FoodProperties.Builder()
            .nutrition(2).saturationMod(2)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION,2000,0),1)
            .build();
}
