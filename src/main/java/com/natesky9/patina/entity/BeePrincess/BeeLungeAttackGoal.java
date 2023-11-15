package com.natesky9.patina.entity.BeePrincess;

import com.natesky9.patina.init.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class BeeLungeAttackGoal extends Goal {
    BeePrincess boss;
    int windup;
    AABB box;
    Vec3 vector;
    public BeeLungeAttackGoal(BeePrincess boss)
    {
        this.boss = boss;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));

    }
    @Override
    public boolean canUse() {
        //if (boss.getTarget() == null || !boss.getTarget().isAlive()) return false;
        return boss.attackCounter % 4==3
                && this.boss.level().getEntitiesOfClass(Player.class, this.boss.getBoundingBox().inflate(3)).isEmpty();
    }

    @Override
    public void start() {
        System.out.println("starting lunge");
        windup = 0;
        if (boss.getTarget() != null)
            boss.getLookControl().setLookAt(boss.getTarget());
        boss.getNavigation().stop();

        vector = boss.getViewVector(1).scale(2).multiply(1, 0, 1);
        box = boss.getBoundingBox().move(vector);

        if (boss.level() instanceof ServerLevel level)
            for (int x = 0; x < 8; x++) {
                double attackX = boss.getX() + vector.x() * x / 3;
                double attackY = boss.getY();
                double attackZ = boss.getZ() + vector.z() * x / 3;
                level.sendParticles(ParticleTypes.ANGRY_VILLAGER, attackX, attackY, attackZ, 1,
                        0,0,0, 0);
            }
        boss.attackCounter++;
    }

    @Override
    public boolean canContinueToUse() {
        return windup < 30;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        System.out.println("finished lunge");
    }

    @Override
    public void tick() {
        boss.getNavigation().stop();
        windup++;
        if (windup % 5 == 0)
            boss.level().playSound(null, boss, SoundEvents.BEEHIVE_WORK, SoundSource.HOSTILE, 1, 1);
        double px;
        double py;
        double pz;
        if (boss.level() instanceof ServerLevel level)
            for (int x = 0;x < 8;x++)
            {
                for (int y = 0;y < 8;y++)
                {
                    for (int z = 0;z < 8;z++)
                    {
                        if (onlyTwo(x,y,z))
                        {
                            for (int i = 0;i < 4;i++)
                            {
                                px = box.move(vector.scale(i)).minX + box.getXsize()/8*x;
                                py = box.move(vector.scale(i)).minY + box.getYsize()/8*y;
                                pz = box.move(vector.scale(i)).minZ + box.getZsize()/8*z;
                                //level.sendParticles(ParticleTypes.ELECTRIC_SPARK,px, py, pz,1,0,0,0,0);

                            }
                            //double px = box.minX + box.getXsize()/8*x;
                            //double py = box.minY + box.getYsize()/8*y;
                            //double pz = box.minZ + box.getZsize()/8*z;
                            //level.sendParticles(ParticleTypes.ELECTRIC_SPARK,px, py, pz,1,0,0,0,0);
//
                            //double px2 = box2.minX + box2.getXsize()/8*x;
                            //double py2 = box2.minY + box2.getYsize()/8*y;
                            //double pz2 = box2.minZ + box2.getZsize()/8*z;
                            //level.sendParticles(ParticleTypes.ELECTRIC_SPARK,px2, py2, pz2,1,0,0,0,0);
//
                            //double px3 = box3.minX + box3.getXsize()/8*x;
                            //double py3 = box3.minY + box3.getYsize()/8*y;
                            //double pz3 = box3.minZ + box3.getZsize()/8*z;
                            //level.sendParticles(ParticleTypes.ELECTRIC_SPARK,px3, py3, pz3,1,0,0,0,0);

                        }
                    }
                }
            }
        if (windup == 20)
        {
            List<LivingEntity> targets = new ArrayList<>();
            for (int i = 0;i < 4;i++)
            {
                targets.addAll(boss.level().getEntitiesOfClass(LivingEntity.class, box.move(vector.scale(i)),
                        entity -> entity.getType() != EntityType.BEE && entity.getType() != ModEntityTypes.BEE_BOSS.get()));
            }
            //List<LivingEntity> targets = boss.level().getEntitiesOfClass(LivingEntity.class, box,
            //        entity -> entity.getType() != EntityType.BEE && entity.getType() != ModEntityTypes.BEE_BOSS.get());
            //targets.addAll(boss.level().getEntitiesOfClass(LivingEntity.class, box2,
            //        entity -> entity.getType() != EntityType.BEE && entity.getType() != ModEntityTypes.BEE_BOSS.get()));
            //targets.addAll(boss.level().getEntitiesOfClass(LivingEntity.class, box3,
            //        entity -> entity.getType() != EntityType.BEE && entity.getType() != ModEntityTypes.BEE_BOSS.get()));
            for (Entity entity:targets)
            {
                entity.setInvulnerable(false);
                entity.hurt(boss.damageSources().mobAttack(boss),12);
                entity.setDeltaMovement(vector.add(0,1,0));
                entity.level().playSound(null,entity.blockPosition(),SoundEvents.BEE_STING,SoundSource.HOSTILE,1,1);
            }
            boss.level().playSound(null,boss, SoundEvents.FIRECHARGE_USE, SoundSource.HOSTILE,1,1);
            boss.setDeltaMovement(vector.add(0,.3,0));

        }
    }
    boolean onlyTwo(int x, int y, int z)
    {
        boolean xTrue = x == 0 || x == 7;
        boolean yTrue = y == 0 || y == 7;
        boolean zTrue = z == 0 || z == 7;
        return xTrue && yTrue && !zTrue
        || xTrue && !yTrue && zTrue
        || !xTrue && yTrue && zTrue;
    }
}
