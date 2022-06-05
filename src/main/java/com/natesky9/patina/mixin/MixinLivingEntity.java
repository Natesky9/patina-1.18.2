package com.natesky9.patina.mixin;

import com.natesky9.patina.util.ILivingEntity;
import com.natesky9.patina.util.IMobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity implements ILivingEntity {

    @Shadow public abstract AttributeMap getAttributes();

    @Shadow public abstract boolean addEffect(MobEffectInstance pEffectInstance);

    public MixinLivingEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;onEffectRemoved(Lnet/minecraft/world/effect/MobEffectInstance;)V",
    at = @At("TAIL"))
            protected void onEffectRemoved(MobEffectInstance pEffect, CallbackInfo ci)
    {
        if (!this.level.isClientSide)
        {
            MobEffect mobeffect = pEffect.getEffect();
            System.out.println("amplifier" + pEffect.getAmplifier());
            if (pEffect.getAmplifier() <1) return;
                MobEffectInstance newpotion = new MobEffectInstance(mobeffect,
                        ((IMobEffectInstance)pEffect).getMaxDuration(),pEffect.getAmplifier()-1);
            this.addEffect(newpotion);
        }
    }
}
