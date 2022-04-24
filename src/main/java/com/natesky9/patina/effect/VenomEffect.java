package com.natesky9.patina.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class VenomEffect extends MobEffect {
    protected VenomEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int intensity) {
        double playerHealth = entity.getHealth();
        if (intensity > playerHealth)
        {
            entity.hurt(ModDamageSource.VENOM,intensity);
        }

        super.applyEffectTick(entity, intensity);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
