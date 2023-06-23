package com.natesky9.patina.event;

import com.natesky9.patina.Enchantment.*;
import com.natesky9.patina.Enchantment.Util.ModEnchantmentUtil;
import com.natesky9.patina.Item.BeeShieldItem;
import com.natesky9.patina.Item.Charms;
import com.natesky9.patina.Item.PigWeaponItem;
import com.natesky9.patina.Patina;
import com.natesky9.patina.Block.GravestoneBlock;
import com.natesky9.patina.init.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Patina.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsForge {
    @SubscribeEvent
    public static void LivingDropsEvent(LivingDropsEvent event) {
        GravestoneBlock.create(event);
        AvariceEnchantment.doEffect(event);
        GreedEnchantment.doEffect(event);
        SoulboundEnchantment.store(event);
    }
    @SubscribeEvent
    public static void PlayerXpEvent(PlayerXpEvent.PickupXp event)
    {
        Charms.experienceCharm(event);
    }

    @SubscribeEvent
    public static void PlayerCloneEvent(PlayerEvent.Clone event) {
        SoulboundEnchantment.restore(event);
    }

    @SubscribeEvent
    public static void LivingDamageEvent(LivingDamageEvent event) {
        //when an entity is damaged
        ApathyEnchantment.doEffect(event);
        WrathEnchantment.doEffect(event);
        PrideEnchantment.doEffect(event);
        CoercionEnchantment.doEffect(event);
        HumilityEnchantment.doEffect(event);

        if (event.getSource().getEntity() instanceof Player player)
        {
            if (player.getItemInHand(player.getUsedItemHand()).is(ModItems.PIG_SWORD.get()))
                PigWeaponItem.trigger(event);
        }
    }

    @SubscribeEvent
    public static void LivingAttackEvent(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        if (!(source.getEntity() instanceof Player player)) return;
        if (entity instanceof TamableAnimal pet) {
            if (!(pet.isOwnedBy(player))) return;
            //can't hurt your own pets unless you're crouching
            event.setCanceled(!player.isCrouching());
        }
        if (entity instanceof AbstractHorse horse) {
            if (!horse.isTamed()) return;
            //can't hurt horses unless you're crouching
            event.setCanceled(!player.isCrouching());
        }

    }

    @SubscribeEvent
    public static void LivingHurtEvent(LivingHurtEvent event) {
        //when an entity is attacking
        RetributionEnchantment.doEffect(event);
        LustEnchantment.doEffect(event);
        ModEnchantmentUtil.netherCursePlayer(event);

        Charms.detonationCharm(event);
        Charms.ambushCharm(event);
    }

    @SubscribeEvent
    public static void LivingUseTotemEvent(LivingUseTotemEvent event) {
        Charms.spawnFragment(event);
        //when a totem is used, drop a charm?
    }

    @SubscribeEvent
    public static void LivingHealEvent(LivingHealEvent event) {
        SlothEnchantment.doEffect(event);
        Charms.vitalityCharm(event);
    }

    @SubscribeEvent
    public static void EatEvent(VanillaGameEvent event) {
        //eat happens after the food is consumed
        if (event.getVanillaEvent() == GameEvent.EAT) {
            GluttonyEnchantment.doEffect(event);
            BrimfulEnchantment.doEffect(event);
        }
    }

    @SubscribeEvent
    public static void MobEffectEvent(MobEffectEvent.Added event) {
        Charms.alchemyCharm(event);
    }

    @SubscribeEvent
    public static void BabyEntitySpawnEvent(BabyEntitySpawnEvent event) {
        Charms.fertilityCharm(event);
    }

    @SubscribeEvent
    public static void WandererTradesEvent(WandererTradesEvent event) {
        //event.getRareTrades().add()
    }

    @SubscribeEvent
    public static void Interact(PlayerInteractEvent.EntityInteract event) {
        MerchantOffer offer = new MerchantOffer(new ItemStack(Items.EMERALD, (int) (Math.random() * 30) + 30),
                new ItemStack(ModItems.CHARM_FERTILITY.get()), 1, 10, 1.0F);
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
    @SubscribeEvent
    public static void ShieldBlockEvent(ShieldBlockEvent event)
    {
        if (event.getEntity() instanceof Player player)
        {
            if (player.getUseItem().is(ModItems.BEE_SHIELD.get()))
                BeeShieldItem.trigger(event);
        }
    }

    @SubscribeEvent
    public static void ExplosionEvent(ExplosionEvent event)
    {
        event.getExplosion().getExploder();
    }
}
