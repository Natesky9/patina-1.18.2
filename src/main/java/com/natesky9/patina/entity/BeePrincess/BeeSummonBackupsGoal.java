package com.natesky9.patina.entity.BeePrincess;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bee;

import java.util.EnumSet;
import java.util.List;

public class BeeSummonBackupsGoal extends Goal {
    BeePrincess boss;
    int attackTimer;
    public BeeSummonBackupsGoal(BeePrincess boss)
    {
        this.boss = boss;
    }
    @Override
    public boolean canUse() {
        System.out.println("triggering");
        List<Bee> bees = boss.level().getEntitiesOfClass(Bee.class, boss.getBoundingBox().inflate(16));
        int count = bees.size();
        for (Bee bee:bees)
        {
            //if it's stung, go back home
            if (bee.hasStung())
            {
                bee.setTarget(boss);
                bee.setAggressive(false);
                bee.setOnGround(true);
                bee.getNavigation().moveTo(boss,2);

                if (bee.distanceTo(boss) < 2 && bee.hasStung())
                {
                    bee.remove(Entity.RemovalReason.DISCARDED);
                    boss.level().playSound(null, boss.blockPosition(), SoundEvents.BEEHIVE_ENTER, SoundSource.HOSTILE, 1, 1);
                }
            }
        }
        int max = 8-(int)(boss.getHealth()/boss.getMaxHealth()*6);
        System.out.println("max bees: " + max);
        return count < max;
    }

    @Override
    public void start() {
        attackTimer = 0;
    }

    @Override
    public void stop() {
        //summon bees
        List<Bee> bees = boss.level().getEntitiesOfClass(Bee.class, boss.getBoundingBox().inflate(16));

        int max = 8-(int)(boss.getHealth()/boss.getMaxHealth()*6);
        if (bees.size() < max) {
            Bee bee = EntityType.BEE.spawn((ServerLevel) boss.level(), this.boss.blockPosition(), MobSpawnType.MOB_SUMMONED);
            if (this.boss.getTarget() != null) {
                bee.setTarget(this.boss.getTarget());
                bee.setAggressive(true);
            }
            boss.level().playSound(null, boss, SoundEvents.BEEHIVE_EXIT, SoundSource.HOSTILE, 1, 1);
            float healthPercent = boss.getHealth()/boss.getMaxHealth();
            if (healthPercent < .5)
            {
                bee.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,1000,2));
                if (healthPercent < .25)
                {
                    bee.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,1000,2));
                }
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return attackTimer < 60;
    }

    @Override
    public void tick() {
        attackTimer++;
        //System.out.println(attackTimer);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
