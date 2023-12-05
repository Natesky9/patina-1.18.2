package com.natesky9.patina.entity.SpiderQueen;

import com.natesky9.patina.entity.SpiderNest.Spidernest;
import com.natesky9.patina.init.ModEntityTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;

import java.util.List;

public class SummonSpidersGoal extends Goal {
    SpiderQueen queen;
    int timer;
    public SummonSpidersGoal(SpiderQueen spider)
    {
        queen = spider;
    }
    @Override
    public boolean canUse() {
        List<Monster> spiders = queen.level().getEntitiesOfClass(Monster.class,queen.getBoundingBox().inflate(8),
                spider -> spider instanceof Spider || spider instanceof Spidernest);
        System.out.println("spiders nearby: " + spiders.size());
        return spiders.size() < 4;
    }

    @Override
    public void start() {
        timer = 0;
    }

    @Override
    public void stop() {
        Spidernest nest = new Spidernest(ModEntityTypes.SPIDER_NEST.get(), queen.level());
        nest.setPos(queen.getPosition(0));
        nest.timer = 100;
        queen.level().playSound(null,queen, SoundEvents.BEEHIVE_EXIT, SoundSource.HOSTILE,queen.getRandom().nextFloat(),1);
        queen.level().addFreshEntity(nest);
        nest.setDeltaMovement(queen.getRandom().nextFloat()-.5,1,queen.getRandom().nextFloat()-.5);
    }

    @Override
    public void tick() {
        timer++;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return timer < 40;
    }
}
