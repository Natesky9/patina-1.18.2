package com.natesky9.patina.entity.SpiderQueen;

import com.natesky9.patina.BossMeleeAttackGoal;
import com.natesky9.patina.init.ModEntityTypes;
import com.natesky9.patina.init.ModItems;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class SpiderQueen extends Spider {
    public SpiderQueen(EntityType<? extends Monster> p_i48576_1_, Level level)
    {
        super(ModEntityTypes.SPIDER_QUEEN.get(), level);
        this.xpReward = 100;
    }
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(ATTACK_DAMAGE,4)
                .add(MAX_HEALTH,100)
                .add(MOVEMENT_SPEED,.5)
                .add(KNOCKBACK_RESISTANCE,1)
                .add(ATTACK_SPEED,.01)
                .add(ATTACK_KNOCKBACK,1);
    }

    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_6)).setDarkenScreen(true);



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(0,new LookAtPlayerGoal(this, Player.class,8));
        this.goalSelector.addGoal(1,new BossMeleeAttackGoal(this,0,true));
        this.goalSelector.addGoal(3,new MoveTowardsTargetGoal(this,1,16));
        this.goalSelector.addGoal(4,new LeapAtTargetGoal(this,2));
        this.targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(this,Player.class,false));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        Entity target = getTarget();
        if (!(target == null))
            this.lookControl.setLookAt(getTarget(),10,10);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }
    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }
    @Override
    public void die(DamageSource p_70645_1_) {
        super.die(p_70645_1_);
        this.bossEvent.removeAllPlayers();
        this.spawnAtLocation(ModItems.SPIDER_NEST.get());
    }
}
