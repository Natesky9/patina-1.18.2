package com.natesky9.patina.entity.BearPrince;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class BearPrince extends Monster {
    private final ServerBossEvent bossEvent =  new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_6);
    public int attackCounter = 0;
    public List<MobEffectInstance> effectList;
    public int hpbar;
    public BearPrince(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.xpReward = 60;
        effectList = List.of(new MobEffectInstance(MobEffects.NIGHT_VISION,80),
                new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,120,2),
                new MobEffectInstance(MobEffects.LEVITATION,40,2),
                new MobEffectInstance(MobEffects.HARM,1,2));
        hpbar = 6;
    }
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(ATTACK_DAMAGE, 4)
                .add(MAX_HEALTH, 300)
                .add(MOVEMENT_SPEED, .2)
                .add(KNOCKBACK_RESISTANCE, 1)
                .add(ATTACK_SPEED, .1)
                .add(ATTACK_KNOCKBACK, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new LookAtPlayerGoal(this, Player.class, 16));
        this.goalSelector.addGoal(1, new BearZoneGoal(this));
        //this.goalSelector.addGoal(2, new BossMeleeAttackGoal(this, 1));
        this.goalSelector.addGoal(3, new BearStarGoal(this));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this,Player.class,false));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.GENERIC_KILL)) return super.hurt(source,amount);
        bossEvent.setProgress(this.getHealth()/this.getMaxHealth());

        boolean done = false;
        hpbar = (int)Math.ceil((getHealth()/getMaxHealth()*6));
        //can only be harmed under night vision OR by its own attacks
        if (source.getEntity() instanceof Player player && player.hasEffect(MobEffects.NIGHT_VISION)
        || source.getDirectEntity() instanceof BearStarEntity star && star.getOwner() != this)
            done =  super.hurt(source, amount);
        if (hpbar != (int)Math.ceil((getHealth()/getMaxHealth()*6)) && getHealth() > 0)
        {//roar, clearing the field
            List<Entity> entities = this.level().getEntitiesOfClass(Entity.class,this.getBoundingBox().inflate(16));
            for (Entity entity:entities)
            {
                if (entity instanceof Player player)
                {
                    player.removeAllEffects();
                    this.level().playSound(null, this.blockPosition(), SoundEvents.POLAR_BEAR_DEATH, SoundSource.HOSTILE, 1, 1);
                    player.hurt(this.damageSources().indirectMagic(player, this), 4);
                    player.setDeltaMovement(player.position().subtract(this.position()).normalize().add(0, 1, 0));
                }
                if (entity instanceof BearStarEntity star)
                {
                    star.setDeltaMovement(0,1,0);
                }
                if (entity instanceof AreaEffectCloud cloud)
                {
                    cloud.remove(RemovalReason.DISCARDED);
                }
            }

        }
        this.hpbar = (int)Math.ceil((getHealth()/getMaxHealth()*6));
        return done;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        bossEvent.addPlayer(player);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return SoundEvents.POLAR_BEAR_HURT;
    }

    @Override
    public void setCustomName(@Nullable Component p_20053_) {
        super.setCustomName(p_20053_);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }
}
