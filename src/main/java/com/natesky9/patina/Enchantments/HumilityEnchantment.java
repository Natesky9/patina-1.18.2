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

public class HumilityEnchantment extends Enchantment implements TriggeredEnchant {
    ItemStack item = new ItemStack(Items.NETHER_STAR);
    public HumilityEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingDamageEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.PRIDEBLESSING.get(), player) == 0) return;
        EnchantCooldowns cooldowns = Patina.enchantmentCooldowns;
        if (cooldowns.isOnCooldown(player,ModEnchantments.PRIDEBLESSING.get())) return;

        LivingEntity entity = event.getEntityLiving();
        float bossHealth = entity.getMaxHealth();
        if (bossHealth/2 < player.getMaxHealth()) return;

        event.setAmount(event.getAmount() + bossHealth/6);
        cooldowns.addCooldown(player,ModEnchantments.PRIDEBLESSING.get(),200);
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }

    @Override
    public String getMessage() {
        return "You land a vicious blow";
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.ENDER_DRAGON_GROWL;
    }

    @Override
    public SoundEvent getSound2() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }
}
