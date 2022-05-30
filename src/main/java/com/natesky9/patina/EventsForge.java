package com.natesky9.patina;

import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.item.KnockbackShieldItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsForge
{
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


        //sourceEntity.kill();


        //if (damageSource == DamageSource.mobAttack())
//
    }
    //
    @SubscribeEvent
    public void PotionRemoveEvent(LivingEntity living, MobEffectInstance effect){
        int duration = effect.getDuration();
        int intensity = effect.getAmplifier();
        MobEffect potion = effect.getEffect();
        System.out.println("We hit the potion event");

        if (intensity >= 1) {
            MobEffectInstance newpotion = new MobEffectInstance(potion,100,intensity-1);
            living.addEffect(newpotion);
        }
    }
}
