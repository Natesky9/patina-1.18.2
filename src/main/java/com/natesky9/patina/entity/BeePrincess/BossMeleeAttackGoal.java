package com.natesky9.patina.entity.BeePrincess;

import com.natesky9.patina.entity.BearPrince.BearPrince;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class BossMeleeAttackGoal extends Goal{

    protected final PathfinderMob mob;
    private final double speedModifier;
    private int ticksUntilNextAttack;

    public BossMeleeAttackGoal(PathfinderMob entity, double moveSpeed) {
        this.mob = entity;
        this.speedModifier = moveSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity == null || !livingentity.isAlive()) return false;
        Path path = this.mob.getNavigation().getPath();
        if (path == null || path.isDone() || !path.canReach())
            path = this.mob.getNavigation().createPath(livingentity, 0);
        if (path != null)
            this.mob.getNavigation().moveTo(path,1);


        return path != null;
    }

    public boolean canContinueToUse() {
        LivingEntity livingentity = this.mob.getTarget();
        return livingentity != null && livingentity.isAlive() && livingentity.isAttackable() && ticksUntilNextAttack < 40;
    }

    public void start() {
        ticksUntilNextAttack = 0;
        System.out.println("starting melee");
        LivingEntity target = this.mob.getTarget();
        if (target == null) return;
        Path path = this.mob.getNavigation().createPath(target, 0);
        this.mob.getNavigation().moveTo(path, this.speedModifier);
        if (mob instanceof BeePrincess bee)
            bee.attackCounter++;
        if (mob instanceof BearPrince bear)
            bear.attackCounter++;
    }

    public void stop() {
        this.mob.getNavigation().stop();
        System.out.println("stopping melle");
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity == null) return;
        this.mob.getLookControl().setLookAt(livingentity, 360.0F, 360.0F);
        Path path = mob.getNavigation().getPath();
        //System.out.println(path);
        //if (mob.level() instanceof ServerLevel level && this.mob.getNavigation().getPath() != null)
        //    for (int i=0;i < this.mob.getNavigation().getPath().getNodeCount();i++)
        //    {
        //        Node node = this.mob.getNavigation().getPath().getNode(i);
        //        level.sendParticles(ParticleTypes.ELECTRIC_SPARK,node.x,node.y,node.z,1,0,0,0,0);
//
        //    }



        path = this.mob.getNavigation().createPath(livingentity, 0);
        this.mob.getNavigation().moveTo(path,1);
        //if (path == null || path.isDone() || !path.canReach())
        //{
        //}


        this.ticksUntilNextAttack = this.ticksUntilNextAttack +1;
        if (this.canPerformAttack(livingentity)) {
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(livingentity);
            //find a better way to reset this
            this.ticksUntilNextAttack = 100;
        }
    }

    protected boolean canPerformAttack(LivingEntity p_301160_) {
        return this.mob.isWithinMeleeAttackRange(p_301160_) && ticksUntilNextAttack > 20;// && this.mob.getSensing().hasLineOfSight(p_301160_);
    }

}
