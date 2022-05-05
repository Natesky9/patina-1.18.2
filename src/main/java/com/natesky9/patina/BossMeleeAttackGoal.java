package com.natesky9.patina;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class BossMeleeAttackGoal extends MeleeAttackGoal {

    public BossMeleeAttackGoal(PathfinderMob entity, double p_i1636_2_, boolean p_i1636_4_) {
        super(entity, p_i1636_2_, p_i1636_4_);
    }
    @Override
    public void start() {
        super.start();
        this.resetAttackCooldown();
    }

    @Override
    public boolean canUse() {
        return (super.canUse() && getTicksUntilNextAttack() <= 0);
    }

    @Override
    protected void resetAttackCooldown() {
        ticksUntilNextAttack = getAttackInterval();
    }

    @Override
    protected int getAttackInterval() {
        return (int)(20/mob.getAttributeValue(Attributes.ATTACK_SPEED));
    }

    //@Override
    //protected double getAttackReachSqr(Entity attackTarget) {
    //    return (double)(Math.pow(mob.getBbWidth(),2)+Math.pow(attackTarget.getBbWidth(),2));
    //}
}
