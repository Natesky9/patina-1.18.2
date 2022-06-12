package com.natesky9.patina.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;

public class EatingEffect extends MobEffect {
    public EatingEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        ((Player) pLivingEntity).getFoodData().eat(0,1f);
        pLivingEntity.setStingerCount(pLivingEntity.getStingerCount()+1);
        System.out.println("ran");
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        System.out.println("running");
        return (pDuration % 20 == 0);
    }
}
