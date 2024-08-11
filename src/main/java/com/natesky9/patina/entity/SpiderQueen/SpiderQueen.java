package com.natesky9.patina.entity.SpiderQueen;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK;

public class SpiderQueen extends Monster {
    private final ServerBossEvent bossEvent = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE,
            BossEvent.BossBarOverlay.NOTCHED_6);
    public SpiderQueen(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(ATTACK_DAMAGE, 4)
                .add(MAX_HEALTH, 300)
                .add(MOVEMENT_SPEED, .5)
                .add(KNOCKBACK_RESISTANCE, 1)
                .add(ATTACK_SPEED, .1)
                .add(ATTACK_KNOCKBACK, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class,16));
        this.goalSelector.addGoal(2, new SummonSpidersGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.4, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this,Player.class,true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        bossEvent.addPlayer(player);
        player.level().playSound(player,player, SoundEvents.SPIDER_AMBIENT, SoundSource.HOSTILE,0,1);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        bossEvent.removePlayer(player);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        bossEvent.setProgress(getHealth()/getMaxHealth());
        return super.hurt(p_21016_, p_21017_);
    }
}
