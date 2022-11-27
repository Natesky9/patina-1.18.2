package com.natesky9.patina.Enchantments;

import com.natesky9.patina.TriggeredEnchant;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class StrifeEnchantment extends Enchantment implements TriggeredEnchant {
    public StrifeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId() + " WIP").withStyle(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public SoundEvent getSound() {
        return null;
    }

    @Override
    public SoundEvent getSound2() {
        return null;
    }

    @Override
    public ItemStack getItem() {
        return null;
    }
}
