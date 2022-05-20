package com.natesky9.patina.entity.SpiderNest;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK;

public class SpiderNestEntity extends Monster {
    public SpiderNestEntity(EntityType<? extends Monster> p_33002_, Level p_33003_)
    {super(p_33002_, p_33003_);}
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(ATTACK_DAMAGE,0)
                .add(MAX_HEALTH,10)
                .add(MOVEMENT_SPEED,0)
                .add(KNOCKBACK_RESISTANCE,1)
                .add(ATTACK_SPEED,0);
    }

    private int spawningTick;

    @Override
    public void tick()
    {
        super.tick();
        //do tick stuff here
        spawningTick++;
        if (spawningTick >60)
        {
            double getx = this.getX()+.5;
            double gety = this.getY()+.5;
            double getz = this.getZ()+.5;
            Spider spider = new Spider(EntityType.SPIDER, this.level);
            spider.setPos(getx,gety,getz);
            this.level.addFreshEntity(spider);
            this.remove(RemovalReason.KILLED);
            this.level.setBlockAndUpdate(this.blockPosition(), Blocks.COBWEB.defaultBlockState());
            //this.level.playSound(null,getx,gety,getz, SoundEvents.SLIME_ATTACK, SoundCategory.HOSTILE,1F,.5F);
        }
    }
}
