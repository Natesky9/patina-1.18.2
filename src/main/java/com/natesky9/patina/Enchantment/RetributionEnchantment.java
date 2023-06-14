package com.natesky9.patina.Enchantment;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.HashMap;
import java.util.Map;

public class RetributionEnchantment extends Enchantment{
    ItemStack item = new ItemStack(Items.GOLDEN_SWORD);
    public RetributionEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    //retribution stores the premitigated damage done
    //and returns it upon attacking the source

    protected static Map<Player, Map<LivingEntity,Float>> retaliatemap = new HashMap<>(new HashMap<>());

    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingHurtEvent event)
    {
        DamageSource source = event.getSource();
        float amount = event.getAmount();
        LivingEntity target = event.getEntity();

        if (!(source.getEntity() instanceof LivingEntity entity)) return;

        //
        //if the player is attacking something
        if (target instanceof ServerPlayer player)
        {
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLESSINGWRATH.get(), target) == 0) return;

            //10% chance to trigger
            if (Math.random() > 0.10) return;
            //player.displayClientMessage(Component.translatable("patina.retribution_trigger"),true);
            player.level.playSound(null,player.blockPosition(), SoundEvents.PIGLIN_BRUTE_ANGRY,
                    SoundSource.PLAYERS,0.5F,0.5F);

            if (!retaliatemap.containsKey(player)) return;
            //exit early if there's nothing stored

            Map<LivingEntity, Float> retaliate = retaliatemap.get(player);

            if (retaliate.containsKey(target))
            {
                float retaliationDamage = retaliate.get(target);
                System.out.println("damage: " + retaliationDamage);
                event.setAmount(event.getAmount() + retaliationDamage);
                Component message = Component.literal("Released damage: " + retaliationDamage).withStyle(ChatFormatting.RED);
                player.displayClientMessage(message, true);
                retaliatemap.remove(player);
            }
        }
        //if the player is attacked
        if (entity instanceof ServerPlayer player)
            {
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLESSINGWRATH.get(), entity) == 0) return;

            retaliatemap.put(player, Map.of(entity, amount));
            entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200));
            Component message = Component.literal("Stored damage: " + amount).withStyle(ChatFormatting.RED);
            player.displayClientMessage(message, true);
            }
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }
}
