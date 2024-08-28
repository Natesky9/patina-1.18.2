package com.natesky9.patina.Item;

import com.natesky9.patina.init.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;

import java.util.List;

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
        entity.level().playSound(null,entity.blockPosition(),SoundEvents.GOAT_RAM_IMPACT, SoundSource.PLAYERS,1F,1F);


    }
    public static void experienceCharm(PlayerXpEvent.PickupXp event)
    {
        //doubles the received xp
        Player player = event.getEntity();
        if (!player.getInventory().hasAnyMatching(itemStack -> itemStack.is(ModItems.CHARM_EXPERIENCE.get()))) return;
        ExperienceOrb orb = event.getOrb();
        orb.value = orb.value*2;
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

        MobEffectInstance instance = event.getEffectInstance();
        MobEffect effect = instance.getEffect().get();

        //only apply to beneficial effects
        if (!(instance.getEffect().get().isBeneficial())) return;

        int duration = instance.getDuration();
        int potency = instance.getAmplifier();

        MobEffectInstance holder;
        while (potency > 0)
        {
            duration += event.getEffectInstance().getDuration();
            potency--;
            holder = new MobEffectInstance(instance.getEffect(),duration,potency);
            //TODO: Access transform this
            //instance.hiddenEffect = holder;
            instance = holder;
        }
        //if (potency > 0)
        //{
        //    instance.hiddenEffect = new MobEffectInstance(
        //            effect,duration*2,potency-1);
        //}

        //potion decay
        //    System.out.println("i is: " + i);
        //    Boolean test = instance.update(new MobEffectInstance(effect,duration*2,i-1));
        //    System.out.println("test is: " + test);
        //    //entity.forceAddEffect(new MobEffectInstance(effect,duration*2,potency-1),player);
        //    //entity.addEffect(new MobEffectInstance(effect,duration*2,potency-1));
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
        if (!(father.level() instanceof ServerLevel level)) return;

        Animal mother = (Animal) event.getParentB();

        AgeableMob copy = father.getBreedOffspring(level, mother);

        copy.setBaby(true);
        copy.moveTo(father.getX(), father.getY(), father.getZ(), 0.0F, 0.0F);
        level.addFreshEntityWithPassengers(copy);
        level.broadcastEntityEvent(copy, (byte)18);

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
    private static ItemStack createLootChest(String loot)
    {
        ItemStack stack = new ItemStack(Items.CHEST);
        CompoundTag tag = new CompoundTag();
        tag.putString("LootTable",loot);
        //if this doesn't work, I was told to try this instead
        //stack.set(DataComponents.CONTAINER_LOOT,)
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        //the old nbt method
        //stack.getOrCreateTag().put("BlockEntityTag",tag);
        return stack;
    }
    private static ItemStack createSuspiciousLoot(Item block, String loot)
    {
        int count = (int)(Math.random()*32);
        ItemStack stack = new ItemStack(block,count);
        CompoundTag tag = new CompoundTag();
        tag.putString("loot_table",loot);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        //stack.getOrCreateTag().put("BlockEntityTag",tag);
        return stack;
    }
    public static void contrabandCharm(PlayerInteractEvent.EntityInteract event)
    {
        if (!(event.getLevel() instanceof ServerLevel)) return;
        List<ItemStack> items = List.of(
            createSuspiciousLoot(Items.SUSPICIOUS_SAND,"minecraft:archaeology/desert_pyramid"),
            createLootChest("minecraft:chests/simple_dungeon"),
            createLootChest("minecraft:chests/ancient_city"),
            createLootChest("minecraft:chests/bastion_other"),
            createLootChest("minecraft:chests/desert_pyramid"),
            createLootChest("minecraft:chests/end_city_treasure"),
            createLootChest("minecraft:chests/jungle_temple"),
            createLootChest("minecraft:chests/nether_bridge"),
            createLootChest("minecraft:chests/pillager_outpost"),
            createLootChest("minecraft:chests/shipwreck_supply"),
            createLootChest("minecraft:chests/abandoned_mineshaft"),
            createLootChest("minecraft:chests/stronghold_library"),
            createLootChest("minecraft:chests/spawn_bonus_chest"),
            createLootChest("minecraft:chests/woodland_mansion")
        );
        int index = (int)(Math.random()* items.size());

        MerchantOffer offer = new MerchantOffer(new ItemCost(Items.EMERALD, (int) (Math.random() * 30) + 30),
                items.get(index), 1, 10, 1.0F);
        Entity entity = event.getTarget();
        Player player = event.getEntity();
        if (!(entity instanceof WanderingTrader trader)) return;
        if (player.getInventory().hasAnyMatching(itemStack -> itemStack.is(ModItems.CHARM_CONTRABAND.get()))) {
            if (trader.getOffers().size() > 6) return;
            trader.getOffers().add(offer);
        } else if (trader.getOffers().size() > 6) {
            trader.getOffers().remove(6);
        }
    }
    public static void spawnFragment(LivingUseTotemEvent event)
    {
        Entity entity = event.getEntity();
        ItemStack stack = new ItemStack(ModItems.CHARM_FRAGMENT.get(),1 + (int)(Math.random()*2));
        entity.spawnAtLocation(stack);
    }
}
