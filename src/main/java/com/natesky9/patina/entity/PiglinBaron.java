package com.natesky9.patina.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK;

public class PiglinBaron extends Monster {
    public PiglinBaron(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(ATTACK_DAMAGE, 4)
                .add(MAX_HEALTH, 300)
                .add(MOVEMENT_SPEED, .5)
                .add(KNOCKBACK_RESISTANCE, 1)
                .add(ATTACK_SPEED, .1)
                .add(ATTACK_KNOCKBACK, 1);
    }
}
