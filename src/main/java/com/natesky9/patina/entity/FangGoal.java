package com.natesky9.patina.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.EvokerFangs;

public class FangGoal extends Goal {
    Monster monster;
    int timer;

    public FangGoal(Monster monster)
    {
        this.monster = monster;
    }
    @Override
    public boolean canUse() {
        Entity target = monster.getTarget();
        return target != null && monster.distanceTo(target) < 16;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        timer++;
        if (timer > 60)
        {
            Entity target = monster.getTarget();
            if (target == null) return;
            double x;
            double y;
            double z;
            for (int i = 1;i <= 4;i++)
            {
                x = monster.getX() + (target.getX()-monster.getX())/4*i;
                y = (int) (monster.getY() + (target.getY()-monster.getY())/4*i);
                z = monster.getZ() + (target.getZ()-monster.getZ())/4*i;
                EvokerFangs fangs = new EvokerFangs(monster.level(),x,y,z,0,i*4,monster);
                monster.level().addFreshEntity(fangs);
            }

            timer = 0;
        }
    }
}
