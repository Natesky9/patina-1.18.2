package com.natesky9.patina;

import com.google.common.collect.Multimap;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.item.KnockbackShieldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.Tag;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsForge
{

    @SubscribeEvent
    public static void LevelTickEvent(TickEvent.WorldTickEvent event)
    {
        //exit if not client
        if (!(event.world instanceof ServerLevel level)) return;
        IncursionManager.tick(level);
    }
    @SubscribeEvent
    public static void LivingDeathEvent(LivingDeathEvent event)
    {
        if (!(event.getEntity().level instanceof ServerLevel level)) return;

        IncursionManager manager = IncursionManager.get(event.getEntity().level);
        manager.entityDie(event.getEntityLiving());
    }
    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event)
    {
        event.register(PlayerCapabilities.class);
    }
    //LivingHurtEvent
    @SubscribeEvent
    public static void ArrowLooseEvent(ArrowLooseEvent event)
    {
        event.setCanceled(true);
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

        if (effect == MobEffects.DAMAGE_BOOST)
        {
            if (entity.getEffect(MobEffects.WEAKNESS) != null)
            {
                entity.removeEffect(MobEffects.WEAKNESS);
                //TODO: figure out how to handle this
                return;
            }
        }

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
