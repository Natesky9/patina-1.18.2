package com.natesky9.patina.event;

import com.natesky9.patina.Enchantment.*;
import com.natesky9.patina.Enchantment.Util.ModEnchantmentUtil;
import com.natesky9.patina.Item.Charms;
import com.natesky9.patina.Patina;
import com.natesky9.patina.Block.GravestoneBlock;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Patina.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsForge {
    @SubscribeEvent
    public static void LivingDropsEvent(LivingDropsEvent event)
    {
        GravestoneBlock.create(event);
        AvariceEnchantment.doEffect(event);
        GreedEnchantment.doEffect(event);
        SoulboundEnchantment.store(event);
    }
    @SubscribeEvent
    public static void PlayerCloneEvent(PlayerEvent.Clone event)
    {
        SoulboundEnchantment.restore(event);
    }
    @SubscribeEvent
    public static void LivingDamageEvent(LivingDamageEvent event)
    {
        //when an entity is damaged
        ApathyEnchantment.doEffect(event);
        WrathEnchantment.doEffect(event);
        PrideEnchantment.doEffect(event);
        CoercionEnchantment.doEffect(event);
        HumilityEnchantment.doEffect(event);

    }
    @SubscribeEvent
    public static void LivingAttackEvent(LivingAttackEvent event)
    {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        if (!(source.getEntity() instanceof Player player)) return;
        if (entity instanceof TamableAnimal pet)
        {
            if (!(pet.isOwnedBy(player))) return;
            //can't hurt your own pets unless you're crouching
            event.setCanceled(!player.isCrouching());
        }
        if (entity instanceof AbstractHorse horse)
        {
            if (!horse.isTamed()) return;
            //can't hurt horses unless you're crouching
            event.setCanceled(!player.isCrouching());
        }

    }
    @SubscribeEvent
    public static void LivingHurtEvent(LivingHurtEvent event)
    {
        //when an entity is attacking
        RetributionEnchantment.doEffect(event);
        LustEnchantment.doEffect(event);
        ModEnchantmentUtil.netherCursePlayer(event);

        Charms.ambushCharm(event);
    }
    @SubscribeEvent
    public static void LivingUseTotemEvent(LivingUseTotemEvent event)
    {
        //when a totem is used, drop a charm?
    }
    @SubscribeEvent
    public static void LivingHealEvent(LivingHealEvent event)
    {
        SlothEnchantment.doEffect(event);
    }
    @SubscribeEvent
    public static void EatEvent(VanillaGameEvent event)
    {
        //eat happens after the food is consumed
        if (event.getVanillaEvent() == GameEvent.EAT)
        {
            GluttonyEnchantment.doEffect(event);
            BrimfulEnchantment.doEffect(event);
        }
    }
}
