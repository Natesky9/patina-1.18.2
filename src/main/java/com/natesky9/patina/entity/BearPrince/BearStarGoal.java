package com.natesky9.patina.entity.BearPrince;

import com.natesky9.patina.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;

public class BearStarGoal extends Goal {
    BearPrince bear;
    int attackTimer;
    public BearStarGoal(BearPrince bear)
    {
        this.bear = bear;
    }
    @Override
    public boolean canUse() {
        return bear.hpbar % 2 == 0;
    }

    @Override
    public void start() {
        attackTimer = 0;
        bear.attackCounter++;
        BearStarEntity star = new BearStarEntity(ModEntityTypes.BEAR_STAR.get(), bear.level());
        star.setPos(bear.position().add(0,7,0));
        star.setDeltaMovement((bear.getRandom().nextFloat()-.5f),0,(bear.getRandom().nextFloat()-.5f));
        star.setOwner(bear);
        bear.level().addFreshEntity(star);
    }

    @Override
    public void stop() {

    }

    @Override
    public void tick() {
        attackTimer++;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return attackTimer < 40-bear.hpbar;
    }
}
