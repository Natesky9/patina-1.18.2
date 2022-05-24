package com.natesky9.patina;

import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.item.KnockbackShieldItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events
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
}
