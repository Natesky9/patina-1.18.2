package com.natesky9.patina.Enchantments;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnvyEnchantment extends Enchantment{

    public EnvyEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_RED);
        //placeholder to test
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }
}
