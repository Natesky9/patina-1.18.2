package com.natesky9.patina.Enchantments;

import com.natesky9.patina.EnchantCooldowns;
import com.natesky9.patina.Patina;
import com.natesky9.patina.TriggeredEnchant;
import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.VanillaGameEvent;

public class BrimfulEnchantment extends Enchantment implements TriggeredEnchant {
    public BrimfulEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    //gluttony blessing sets the players saturation to max upon eating
    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(VanillaGameEvent event)
    {
        if (event.getCause() instanceof Player player)
        {
            boolean gluttonyBlessed = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GLUTTONYBLESSING.get(), player) > 0;
            if (!gluttonyBlessed) return;
            EnchantCooldowns cooldowns = Patina.enchantmentCooldowns;
            if (cooldowns.isOnCooldown(player, ModEnchantments.GLUTTONYBLESSING.get())) return;
            cooldowns.addCooldown(player,ModEnchantments.GLUTTONYBLESSING.get(),2000);

            FoodData foodData = player.getFoodData();
            foodData.setSaturation(20);
            foodData.setFoodLevel(20);
            player.displayClientMessage(new TextComponent("You feel nourished"),true);
        }
    }

    @Override
    public void onCooldown(Player player) {
        TriggeredEnchant.message(player,"You feel nourished");
        TriggeredEnchant.playSound(player, SoundEvents.BUNDLE_DROP_CONTENTS);
        }

    @Override
    public void offCooldown(Player player) {
        TriggeredEnchant.playSound(player,SoundEvents.PIG_AMBIENT);
    }
}
