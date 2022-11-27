package com.natesky9.patina.Enchantments;

import com.natesky9.patina.EnchantCooldowns;
import com.natesky9.patina.Patina;
import com.natesky9.patina.TriggeredEnchant;
import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class ApathyEnchantment extends Enchantment implements TriggeredEnchant {
    static ItemStack item = new ItemStack(Items.RED_BED);
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
    //
    @Override
    public String getMessage() {
        return "You shrug off the blow";
    }
    @Override
    public SoundEvent getSound() {
        return SoundEvents.SHIELD_BLOCK;
    }
    @Override
    public SoundEvent getSound2() {
        return SoundEvents.BASALT_PLACE;
    }
    @Override
    public ItemStack getItem() {
        return item;
    }
}
