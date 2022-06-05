package com.natesky9.patina.mixin;

import com.natesky9.patina.util.IMobEffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MobEffectInstance.class)
public abstract class MixinMobEffectInstance
        implements IMobEffectInstance,
        Comparable<MobEffectInstance>,
        net.minecraftforge.common.extensions.IForgeMobEffectInstance
{
    private int maxDuration;

    @Override
    public int getMaxDuration()
    {
        return this.maxDuration;
    }
    @Override
    public void setMaxDuration(int duration) {
        this.maxDuration = duration;
    }

}
