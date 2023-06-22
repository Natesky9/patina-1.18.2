package com.natesky9.patina.Item;

import com.natesky9.patina.init.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.*;

public class Charms {
    public static void ambushCharm(LivingHurtEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        if (!player.getInventory().hasAnyMatching
                (itemStack -> itemStack.is(ModItems.CHARM_AMBUSH.get()))) return;

        float damage = event.getAmount();
        float height = player.fallDistance;
        if (height < 3) return;

        event.setAmount(damage + (height-3));
        //change the fall distance
        player.fallDistance = 0;
        LivingEntity entity = event.getEntity();
        entity.level.playSound(null,entity.blockPosition(),SoundEvents.GOAT_RAM_IMPACT, SoundSource.PLAYERS,1F,1F);


    }
    public static void vitalityCharm(LivingHealEvent event)
    {
        if (!(event.getEntity() instanceof Player player)) return;
        boolean has = player.getInventory().hasAnyMatching(itemStack -> itemStack.is(ModItems.CHARM_VITALITY.get()));
        if (!has) return;

        if (event.getAmount() <= 1) return;
        event.setAmount(event.getAmount()+1);
        float healing = event.getAmount();

        float missingHealth = player.getMaxHealth()-player.getHealth();
        if (healing > missingHealth)
        {
            //excess healing gets turned into absorption hearts, 50% efficiency
            float overheal = healing - missingHealth;
            MobEffectInstance effect = new MobEffectInstance(MobEffects.ABSORPTION,20*30,(int)(overheal/2));
            player.addEffect(effect);
        }
    }
    public static void alchemyCharm(MobEffectEvent.Added event)
    {
        LivingEntity entity = event.getEntity();
        //only applied to players
        if (!(entity instanceof Player player)) return;
        //with an alchemical charm in inventory
        if (!(player.getInventory().hasAnyMatching
                (itemStack -> itemStack.is(ModItems.CHARM_ALCHEMY.get())))) return;

        MobEffectInstance effect = event.getEffectInstance();

        //only apply to beneficial effects
        if (!(effect.getEffect().isBeneficial())) return;

        int duration = effect.getDuration();
        int potency = effect.getAmplifier();

        //potion decay
        if (potency > 0)
        {
            entity.addEffect(new MobEffectInstance(effect.getEffect(),duration*2,potency-1));
        }
    }
    public static void fertilityCharm(BabyEntitySpawnEvent event)
    {
        Player player = event.getCausedByPlayer();
        if (player == null) return;
        if (!(player.getInventory().hasAnyMatching
                (itemStack -> itemStack.is(ModItems.CHARM_FERTILITY.get())))) return;
        //duplicate the baby
        //congratulations, it's twins!
        if (!(event.getParentA() instanceof Animal father)) return;
        if (!(father.level instanceof ServerLevel level)) return;

        Animal mother = (Animal) event.getParentB();
        father.spawnChildFromBreeding(level,mother);

        level.playSound(null,player.blockPosition(),SoundEvents.CAT_PURREOW,SoundSource.PLAYERS);
    }
    public static void detonationCharm(LivingHurtEvent event)
    {
        DamageSource source = event.getSource();
        if (source.is(DamageTypes.FIREWORKS) || source.is(DamageTypes.PLAYER_EXPLOSION) || source.is(DamageTypes.EXPLOSION))
        {
            if (event.getSource().getEntity() instanceof Player player)
            {
                //if the player is cause,
                if (!(player.getInventory().hasAnyMatching(itemStack -> itemStack.is(ModItems.CHARM_DETONATION.get())))) return;
                if (event.getEntity() == player)
                    event.setAmount(event.getAmount()/2);
                else
                    event.setAmount(event.getAmount() * 2);
                return;
            }
            if (event.getEntity() instanceof Player player)
            {
                //otherwise, reduce damage
                if (!(player.getInventory().hasAnyMatching(itemStack -> itemStack.is(ModItems.CHARM_DETONATION.get())))) return;
                event.setAmount(event.getAmount()/2);
            }
        }
    }
    public static void spawnFragment(LivingUseTotemEvent event)
    {
        Entity entity = event.getEntity();
        ItemStack stack = new ItemStack(ModItems.CHARM_FRAGMENT.get(),2 + (int)(Math.random()*4));
        entity.spawnAtLocation(stack);
    }
}
