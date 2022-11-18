package com.natesky9.patina.Enchantments;

import com.natesky9.patina.EnchantCooldowns;
import com.natesky9.patina.Patina;
import com.natesky9.patina.TriggeredEnchant;
import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class ApathyEnchantment extends Enchantment implements TriggeredEnchant {
    public ApathyEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        if (!(entity instanceof Player player)) return;
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SLOTHBLESSING.get(), player) == 0) return;
        EnchantCooldowns cooldowns = Patina.enchantmentCooldowns;
        if (cooldowns.isOnCooldown(player,ModEnchantments.SLOTHBLESSING.get())) return;

        cooldowns.addCooldown(player,ModEnchantments.SLOTHBLESSING.get(),200);
        event.setCanceled(true);
    }

    @Override
    public void onCooldown(Player player) {
        TriggeredEnchant.message(player,"You shrug off the blow");
        TriggeredEnchant.playSound(player, SoundEvents.SHIELD_BLOCK);
    }

    @Override
    public void offCooldown(Player player) {
        TriggeredEnchant.playSound(player,SoundEvents.BASALT_PLACE);
    }
}
