package com.natesky9.patina.entity.SpiderNest;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Spidernest extends Monster {
    public int timer;
    public Spidernest(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.timer = 60;
        //set the timer after spawning
    }
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,10)
                .add(Attributes.KNOCKBACK_RESISTANCE,0);
    }

    @Override
    public void tick() {
        super.tick();
        timer--;
        if (timer < 0 && isAlive())
        {
            CaveSpider spider = new CaveSpider(EntityType.CAVE_SPIDER,this.level());
            spider.setPos(this.getPosition(0));
            this.level().addFreshEntity(spider);
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return SoundEvents.WOOL_BREAK;
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        return source.is(DamageTypes.FALL) ? false:super.hurt(source, p_21017_);
    }
}
