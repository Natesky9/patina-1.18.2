package com.natesky9.patina.entity.BeeQueen;

import com.natesky9.patina.BossMeleeAttackGoal;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class BeeQueen extends Monster implements PowerableMob {
    public BeeQueen(EntityType<? extends Monster> entityType, Level level)
    {
        super(entityType, level);
        this.xpReward = 100;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(ATTACK_DAMAGE,4)
                .add(MAX_HEALTH,300)
                .add(MOVEMENT_SPEED,.5)
                .add(KNOCKBACK_RESISTANCE,1)
                .add(ATTACK_SPEED,.1)
                .add(ATTACK_KNOCKBACK,1);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(0,new LookAtPlayerGoal(this, Player.class,8));
        this.goalSelector.addGoal(2,new BossMeleeAttackGoal(this,1,true));
        this.goalSelector.addGoal(3,new MoveTowardsTargetGoal(this,1,8));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers(Bee.class));
        this.targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(this, Player.class,true));
    }


    private static int spawntick;
    private static boolean adblock;

    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.BEE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BEE_DEATH;
    }

    public float hp(float testhealth)
    {return testhealth/this.getMaxHealth();}

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (adblock)
            return false;
        if (source.isProjectile()) return false;//immune to arrows


        float prehealth = this.getHealth();
        boolean toreturn = super.hurt(source, amount);
        float posthealth = this.getHealth();
        if (prehealth != posthealth)
        {//damage loot
            boolean loot = (prehealth-posthealth >= random.nextFloat()*10);
            if (loot)
            {//shear off a honeycomb
                this.spawnAtLocation(Items.HONEYCOMB);
                level.playSound(null, this, SoundEvents.BEEHIVE_SHEAR,
                        SoundSource.HOSTILE, 1, random.nextFloat() / 2 + .5F);
            }
        }

        if (hp(posthealth) < .75 && hp(prehealth) >= .75)//75% health phase
        {//phase 1
            System.out.println("starting phase 1");
            BeeDefensin1(6,1);
            adblock = true;
        }


        if (hp(posthealth) < .5 && hp(prehealth) >= .5)//50% health phase
        {
            System.out.println("starting phase 2");
            BeeDefensin1(6,2);
            adblock = true;
        }
        if (hp(posthealth) < .25 && hp(prehealth) >= .25)
        {
            System.out.println("starting phase 3");
            BeeDefensin1(6,3);
            adblock = true;
        }
        return toreturn;
    }

    private void BeeDefensin1(int count, int phase) {
        this.level.playSound(null,this,SoundEvents.BEEHIVE_EXIT, SoundSource.NEUTRAL,1,random.nextFloat());
        for (int i=0;i<count;i++)
        {
            Bee bee = new Bee(EntityType.BEE, this.level);
            bee.setPos(this.getX(),this.getY(),this.getZ());
            this.level.addFreshEntity(bee);
            bee.setTarget(this.getTarget());
            bee.addEffect(new MobEffectInstance(MobEffects.GLOWING,12000,0));

            bee.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 12000, phase));
            bee.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12000, phase));
            bee.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000, phase));
        }
    }

    @Override
    public void aiStep() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

        this.level.addParticle(ParticleTypes.FALLING_HONEY,//spawn the dripping honey on boss
                x  + this.random.nextGaussian() * (double)0.3F,
                y+1 + this.random.nextGaussian() * (double)0.3F,
                z + this.random.nextGaussian() * (double)0.3F,
                0.0D, 0.1D, 0.0D);

        spawntick--;
        if (spawntick <=0)
        {
            spawntick = 20;
            List<Bee> nearbyBees = this.level.getEntitiesOfClass(Bee.class, this.getBoundingBox().inflate(16));

            for(Bee beeentity : nearbyBees)
            {//refill the stingers
                double beex = beeentity.getX();
                double beey = beeentity.getY();
                double beez = beeentity.getZ();
                getCommandSenderWorld().addParticle(ParticleTypes.ANGRY_VILLAGER,
                        beex,beey,beez, 0,-.5,0);
                beeentity.setHasStung(false);

            }

            if (nearbyBees.size() < 4 && !adblock)//if there are less bees and not in adblock phase
            {//spawn bees
                if (getTarget() != null && random.nextFloat()>.5)//this is to make sure a player is targeted
                {
                    BeeDefensin1(1,0);
                    return;
                }
            }
            if (adblock && nearbyBees.size() == 0)
            {
                System.out.println("ending adblock phase");
                adblock = false;
                this.level.playSound(null,this,SoundEvents.PHANTOM_AMBIENT,
                        SoundSource.HOSTILE,1,random.nextFloat());
            }
        }
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
        //spawn loot
        ItemStack loot = new ItemStack(ModItems.ROYAL_JELLY.get(), random.nextInt(16));
        this.spawnAtLocation(loot).setGlowingTag(true);
        spawnFragment();
        List<Bee> beeEntities = this.level.getEntitiesOfClass(Bee.class,this.getBoundingBox().inflate(16));
        for(Bee beeentity : beeEntities)
        {
            System.out.println("clearing and healing bees");
            beeentity.setTarget(null);
            beeentity.removeAllEffects();
            beeentity.heal(10);
        }
    }

    private void spawnFragment() {
        Item fragment = null;
        int roll = random.nextInt(4);
        fragment = switch (roll) {
            case 1 -> ModItems.BEE_FRAGMENT_1.get();
            case 2 -> ModItems.BEE_FRAGMENT_2.get();
            case 3 -> ModItems.BEE_FRAGMENT_3.get();
            default -> ModItems.BEE_FRAGMENT_4.get();
        };
        ItemEntity loot = this.spawnAtLocation(fragment);
        if (loot != null)
        {loot.setGlowingTag(true);}


    }

    @Override
    public boolean isPowered() {
        return adblock;
    }
}
