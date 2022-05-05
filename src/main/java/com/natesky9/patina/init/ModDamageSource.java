package com.natesky9.patina.init;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource extends DamageSource{
    public static final DamageSource VENOM = new DamageSource("venom").bypassArmor();

    public ModDamageSource(String p_19333_) {
        super(p_19333_);
    }
}
