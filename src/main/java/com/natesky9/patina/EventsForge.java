package com.natesky9.patina;

import com.google.common.collect.Multimap;
import com.natesky9.patina.Enchantments.*;
import com.natesky9.patina.init.ModEnchantments;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.item.KnockbackShieldItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsForge
{
    @SubscribeEvent
    public static void TickEvent(TickEvent event)
    {
        switch (event.type)
        {
            case SERVER, CLIENT -> Patina.enchantmentCooldowns.tick();
            case WORLD ->
                    {
                        if (!(((TickEvent.WorldTickEvent) event).world instanceof ServerLevel level)) return;
                        IncursionManager.tick(level);
                    }
        }
    }
    //@SubscribeEvent
    //public static void LevelTickEvent(TickEvent.WorldTickEvent event)
    //{
    //    //exit if not client
    //    if (!(event.world instanceof ServerLevel level)) return;
    //    IncursionManager.tick(level);
    //}
    @SubscribeEvent
    public static void LivingDeathEvent(LivingDeathEvent event)
    {
        if (!(event.getEntity().level instanceof ServerLevel level)) return;
        IncursionManager manager = IncursionManager.get(event.getEntity().level);
        manager.entityDie(event.getEntityLiving());

    }
    @SubscribeEvent
    public static void PlayerCloneEvent(PlayerEvent.Clone event)
    {
        SoulboundEnchantment.restore(event);
    }

    @SubscribeEvent
    public static void LivingDropsEvent(LivingDropsEvent event)
    {
        GreedEnchantment.doEffect(event);
        AvariceEnchantment.doEffect(event);
        SoulboundEnchantment.store(event);

        //prevent incursion mobs from dropping items
        //if (!(event.getSource().getEntity() instanceof Player)) return;

        Entity entity = event.getEntity();
        Level level = entity.level;
        IncursionManager manager = IncursionManager.get(level);
        if (manager.isWithinIncursion(event.getEntity().blockPosition()))
        {
            //add all dropped mob items to the incursion
            Incursion incursion = manager.getIncursion(level,entity.blockPosition());
            Collection<ItemEntity> items = event.getDrops();
            incursion.addItems(items);
            event.getDrops().removeAll(items);
        }

    }
    @SubscribeEvent
    public static void LivingDamageEvent(LivingDamageEvent event)
    {
        //when an entity is damaged
        ApathyEnchantment.doEffect(event);
        WrathEnchantment.doEffect(event);
        PrideEnchantment.doEffect(event);
        CoercionEnchantment.doEffect(event);

    }
    @SubscribeEvent
    public static void LivingHurtEvent(LivingHurtEvent event)
    {
        //when an entity is attacking
        RetributionEnchantment.doEffect(event);
        LustEnchantment.doEffect(event);
    }
    @SubscribeEvent
    public static void LivingHealEvent(LivingHealEvent event)
    {
        SlothEnchantment.doEffect(event);
    }
    @SubscribeEvent
    public static void EatEvent(VanillaGameEvent event)
    {
        if (event.getVanillaEvent() == GameEvent.EAT)
        {
            GluttonyEnchantment.doEffect(event);
            BrimfulEnchantment.doEffect(event);
        }
    }

    @SubscribeEvent
    public static void ShieldBlockEvent(ShieldBlockEvent event) {

        LivingEntity blocker = event.getEntityLiving();
        ItemStack itemStack = blocker.getItemInHand(blocker.getUsedItemHand());
        //only do this with our shield
        if (!itemStack.is(ModItems.PISTON_SHIELD.get()))
            return;
        DamageSource damageSource = event.getDamageSource();
        KnockbackShieldItem.activate(blocker,itemStack,damageSource);
        System.out.println("Event Fired!");
    }
    @SubscribeEvent
    public static Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemAttributeModifierEvent event)
    {
        return event.getModifiers();
    }
    @SubscribeEvent
    public static void LivingEquipmentChangeEvent(LivingEquipmentChangeEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        EquipmentSlot slot = event.getSlot();
        ItemStack from = event.getFrom();
        ItemStack to = event.getTo();
    }
    @SubscribeEvent
    public static void PotionAddedEvent(PotionEvent.PotionAddedEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        MobEffectInstance newEffect = event.getPotionEffect();

        MobEffect effect = newEffect.getEffect();
        int duration = newEffect.getDuration();
        int potency = newEffect.getAmplifier();

        //potion decay
        if (potency > 0)
        {
            //System.out.println("Added new potion of amplifier " + potency);
            entity.addEffect(new MobEffectInstance(
                    effect,duration*2,potency-1));
        }
    }
    //

}
