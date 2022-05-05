package com.natesky9.patina.item;

import com.natesky9.patina.init.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class VenomSwordItem extends SwordItem {
    public VenomSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(ModEffects.VENOM.get(),100));
        return super.hurtEnemy(p_43278_, target, attacker);
    }
}
