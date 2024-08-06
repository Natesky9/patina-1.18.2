//package com.natesky9.patina.Enchantment;
//
//import com.natesky9.patina.Enchantment.Util.ModEnchantmentUtil;
//import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//
//public class EnvyEnchantment extends Enchantment{
//
//    public EnvyEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
//        super(pRarity, pCategory, pApplicableSlots);
//    }
//
//    @Override
//    public boolean isCurse() {
//        return true;
//    }
//
//    @Override
//    public Component getFullname(int pLevel) {
//        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_RED);
//        //placeholder to test
//    }
//    @Override
//    protected boolean checkCompatibility(Enchantment pOther) {
//        return ModEnchantmentUtil.sins.contains(pOther);
//    }
//}
//