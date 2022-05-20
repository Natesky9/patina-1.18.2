package com.natesky9.patina.entity.PigKing;

import com.natesky9.patina.init.ModEntityTypes;
import com.natesky9.patina.init.ModItems;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class PigKing extends Monster {
    public PigKing(EntityType<? extends Monster> p_i48576_1_, Level level)
    {
        super(ModEntityTypes.PIGLIN_KING.get(), level);
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

    private final ServerBossEvent hungerProgress = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);
    private final ServerBossEvent saturationProgress = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);
    public ItemStack food = ItemStack.EMPTY;
    float hunger = 0;
    float saturation = 0;
    float hungerMax = 100;
    float saturationMax = 10;

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack item = pPlayer.getItemInHand(pHand);
        if (item.isEdible())
        {
            hunger += item.getFoodProperties(this).getNutrition();
            saturation += item.getFoodProperties(this).getSaturationModifier();
            spawnAtLocation(Items.GOLD_NUGGET);
            pPlayer.level.playSound(null,this,
                    SoundEvents.PIGLIN_CELEBRATE, SoundSource.AMBIENT,1,random.nextFloat()/2+.5F);
            return InteractionResult.CONSUME;
        }
        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new LookAtPlayerGoal(this, Player.class,8));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.hungerProgress.setProgress(this.hunger/this.hungerMax);
        this.saturationProgress.setProgress(this.saturation/this.saturationMax);

        if (hunger > hungerMax || saturation > saturationMax)
        {
            this.kill();
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.hungerProgress.addPlayer(player);
        this.saturationProgress.addPlayer(player);
    }
    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.hungerProgress.removePlayer(player);
        this.saturationProgress.removePlayer(player);
    }
    @Override
    public void die(DamageSource p_70645_1_) {
        super.die(p_70645_1_);
        this.hungerProgress.removeAllPlayers();
        this.saturationProgress.removeAllPlayers();
        if (saturation > saturationMax && hunger > hungerMax)
        {
            //spawn the hunger+saturation reward
            spawnAtLocation(Items.NETHER_STAR).setGlowingTag(true);
            return;
        }
        if (hunger > hungerMax)
        {
            //spawn the hunger reward
            spawnFragment();
            return;
        }
        if (saturation > saturationMax)
        {
            //spawn the saturation reward
            spawnAtLocation(ModItems.PIGLIN_BALLISTA.get()).setGlowingTag(true);
        }
    }
    private void spawnFragment() {
        int roll = random.nextInt(4);
        Item fragment = switch (roll)
                {
                case 1 -> ModItems.PIG_FRAGMENT_1.get();
                case 2 -> ModItems.PIG_FRAGMENT_2.get();
                case 3 -> ModItems.PIG_FRAGMENT_3.get();
                default -> ModItems.PIG_FRAGMENT_4.get();
                };
        this.spawnAtLocation(fragment).setGlowingTag(true);
    }
}
