package com.natesky9.patina.entity.BeePrincess;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK;

public class BeePrincess extends Monster{
    private final ServerBossEvent bossEvent =  new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_6);
    public int attackCounter = 0;

    //
    public BeePrincess(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.xpReward = 60;
    }
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(ATTACK_DAMAGE, 4)
                .add(MAX_HEALTH, 300)
                .add(MOVEMENT_SPEED, .25)
                .add(KNOCKBACK_RESISTANCE, 1)
                .add(ATTACK_SPEED, 1F/4)
                .add(ATTACK_KNOCKBACK, 1);
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class,8));
        this.goalSelector.addGoal(0, new BeeSummonBackupsGoal(this));
        this.goalSelector.addGoal(1, new BeeLungeAttackGoal(this));
        this.goalSelector.addGoal(2, new BeeCoatAttackGoal(this));
        this.goalSelector.addGoal(3, new BossMeleeAttackGoal(this, 1));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        //add to target goal later: .setAlertOthers(Bee.class)
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class,false));
    }

    @Override
    public void push(Entity entity) {
        if (entity.getType() != EntityType.BEE)
            super.push(entity);
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        //don't take fall damage
        if (source.is(DamageTypes.FALL)) return false;

        //ignore "cheese"
        if (source.getEntity() != null && source.getEntity().distanceTo(this) > 16)
            return false;

        List<Bee> bees = this.level().getEntitiesOfClass(Bee.class, this.getBoundingBox().inflate(16),bee -> !bee.hasStung());
        for (Bee bee:bees)
        {//alert nearby bees
            if (source.getEntity() instanceof Player player) {
                bee.setPersistentAngerTarget(player.getUUID());
                bee.startPersistentAngerTimer();
            }
        }

        float healthPercent = this.getHealth()/this.getMaxHealth();
        //every 1/6th health, spawn another bee
        int hpbar = (int)(healthPercent*6);
        System.out.println(hpbar);
        boolean done = super.hurt(source, p_21017_);

        healthPercent = this.getHealth()/this.getMaxHealth();
        if (hpbar != (int)(healthPercent*6))
        {
            Bee bee = EntityType.BEE.spawn((ServerLevel) this.level(), this.blockPosition(), MobSpawnType.MOB_SUMMONED);
            if (this.getTarget() != null && bee != null)
            {
                bee.setTarget(this.getTarget());
                bee.setAggressive(true);
            }
        }


        return done;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        bossEvent.addPlayer(player);
    }

    @Override
    public void setCustomName(@Nullable Component p_20053_) {
        super.setCustomName(p_20053_);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return SoundEvents.BEE_HURT;
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public void die(DamageSource p_21014_) {

        List<Bee> bees = this.level().getEntitiesOfClass(Bee.class, this.getBoundingBox().inflate(16));
        for (Bee bee:bees)
            bee.remove(RemovalReason.DISCARDED);
        super.die(p_21014_);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth()/this.getMaxHealth());
        //System.out.println("attack: " + attackCounter);
        //System.out.println("attack counter: " + this.attackCounter);
    }
}
