package com.natesky9.patina.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties MEATBALLS = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f)
            .build();
    public static final FoodProperties HONEY_NUGGETS = new FoodProperties.Builder().nutrition(10).saturationModifier(1.2f)
            .build();
    public static final FoodProperties CHILI = new FoodProperties.Builder().nutrition(8).saturationModifier(.8f)
            .build();
    public static final FoodProperties ICECREAM = new FoodProperties.Builder().nutrition(3).saturationModifier(1.2f)
            .alwaysEdible()
            .build();
    public static final FoodProperties BURGER = new FoodProperties.Builder().nutrition(12).saturationModifier(.5f)
            .build();
    public static final FoodProperties SKEWERS = new FoodProperties.Builder().nutrition(6).saturationModifier(.4f)
            .build();
    public static final FoodProperties SWEETS = new FoodProperties.Builder().nutrition(1).saturationModifier(2f)
            .alwaysEdible()
            .build();
    public static final FoodProperties APPLE_PIE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.2f)
            .build();
    public static final FoodProperties BROWNIE = new FoodProperties.Builder().nutrition(2).saturationModifier(1f)
            .alwaysEdible()
            .build();
    static final MobEffectInstance meat_treat = new MobEffectInstance(MobEffects.REGENERATION,60,0,false,false,false);
    public static final FoodProperties TRIPLE = new FoodProperties.Builder().nutrition(8).saturationModifier(1f)
            .alwaysEdible().effect(meat_treat,1f)
            .build();
    public static final FoodProperties PIEROGI = new FoodProperties.Builder().nutrition(14).saturationModifier(1f)
            .build();
    public static final FoodProperties OMLETTE = new FoodProperties.Builder().nutrition(8).saturationModifier(.6f)
            .build();
    public static final FoodProperties LOAF = new FoodProperties.Builder().nutrition(6).saturationModifier(.5f)
            .build();

    static final MobEffectInstance cookie = new MobEffectInstance(MobEffects.SATURATION,12000,0,false,false,true);
    public static final FoodProperties GOLDEN_COOKIE = new FoodProperties.Builder().effect(cookie,1f).build();

}
