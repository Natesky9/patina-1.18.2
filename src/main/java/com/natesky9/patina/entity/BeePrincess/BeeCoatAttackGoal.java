package com.natesky9.patina.entity.BeePrincess;

import net.minecraft.Util;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;
import java.util.List;

public class BeeCoatAttackGoal extends Goal {
    BeePrincess bee;
    int attackTimer;
    public BeeCoatAttackGoal(BeePrincess bee)
    {
        this.bee = bee;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }
    @Override
    public boolean canUse() {
        return bee.attackCounter % 4 == 3
                && !bee.level().getEntitiesOfClass(Player.class, bee.getBoundingBox().inflate(3)).isEmpty();
    }

    @Override
    public void start() {
        System.out.println("coat attack");
        bee.getNavigation().stop();
        //bee.level().playSound(null, bee.blockPosition(), SoundEvents.BEE_HURT, SoundSource.HOSTILE, 1, 1);
        attackTimer = 0;
        bee.attackCounter++;
    }

    @Override
    public void stop() {
        System.out.println("done coat attack");
        List<Player> entities = bee.level().getEntitiesOfClass(Player.class, bee.getBoundingBox().inflate(3));
        for (Player player:entities)
        {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,80,1));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,80,3));
            player.hurt(bee.damageSources().indirectMagic(player,bee),4);
        }
        bee.level().playSound(null,bee.blockPosition(), SoundEvents.HONEY_BLOCK_PLACE, SoundSource.HOSTILE,1,1);
        if (bee.level() instanceof ServerLevel level)
            for (int x = -4;x < 6;x++)
            {
                for (int z = -4;z < 6;z++)
                {
                    int count = Math.abs(x)+Math.abs(z);
                    if (count > 7) continue;
                    double px = bee.getX()+x;
                    double py = bee.getY()+2-count/4d;
                    double pz = bee.getZ()+z;
                    level.sendParticles(ParticleTypes.FALLING_HONEY,px,py,pz,1, .1,0,.1,0);
                }
            }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return attackTimer < 20;
    }

    @Override
    public void tick() {
        attackTimer++;
        if (attackTimer % 5 == 0)
            bee.level().playSound(null, bee.blockPosition(), SoundEvents.HONEY_DRINK, SoundSource.HOSTILE, 1, 1);
    }
}
