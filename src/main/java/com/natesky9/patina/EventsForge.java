package com.natesky9.patina;

import com.google.common.collect.Multimap;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.item.KnockbackShieldItem;
import com.natesky9.patina.util.IMobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsForge
{
    //@SubscribeEvent
    //public static void PotionRemoveEvent(PotionEvent.PotionRemoveEvent event)
    //{
    //    MobEffectInstance effect = event.getPotionEffect();
    //    LivingEntity getentity = event.getEntityLiving();
    //    int intensity = effect.getAmplifier();
    //    int duration = effect.getDuration();//duration not used here
    //    int maxDuration = ((IMobEffectInstance)(Object)effect).getMaxDuration();
    //    MobEffect potion = effect.getEffect();
//
    //    MobEffectInstance newpotion = new MobEffectInstance(potion,maxDuration,intensity-1);
    //    getentity.addEffect(newpotion);
    //    System.out.println("canceled");
    //}
    //@SubscribeEvent
    //public static void PotionExpiryEvent(PotionEvent.PotionExpiryEvent event){
    //    MobEffectInstance effect = event.getPotionEffect();
    //    LivingEntity getentity = event.getEntityLiving();
    //    int intensity = effect.getAmplifier();
    //    int duration = effect.getDuration();//duration not used here
    //    int maxDuration = ((IMobEffectInstance)(Object)effect).getMaxDuration();
    //    MobEffect potion = effect.getEffect();
    //    System.out.println("We hit the potion event");
    //    getentity.removeEffect(potion);
    //}
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
        System.out.println("Fired Forge hook!");
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
        MobEffectInstance oldEffect = event.getOldPotionEffect();
        MobEffectInstance newEffect = event.getPotionEffect();
        Entity source = event.getPotionSource();

        int duration = newEffect.getDuration();
        ((IMobEffectInstance)newEffect).setMaxDuration(duration);
    }
    //

}
