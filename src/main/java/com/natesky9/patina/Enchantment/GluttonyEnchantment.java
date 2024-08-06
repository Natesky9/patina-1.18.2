//package com.natesky9.patina.Enchantment;
//
//import com.natesky9.patina.Enchantment.Util.ModEnchantmentUtil;
//import com.natesky9.patina.init.ModEnchantments;
//import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.food.FoodData;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraftforge.event.VanillaGameEvent;
//
//public class GluttonyEnchantment extends Enchantment {
//    public GluttonyEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
//        super(pRarity, pCategory, pApplicableSlots);
//    }
//    //curse of gluttony halves the players saturation
//    //whenever they try to eat anything
//
//    @Override
//    public boolean isCurse() {
//        return true;
//    }
//
//    public static void doEffect(VanillaGameEvent event)
//    {
//        if (event.getCause() instanceof Player player)
//        {
//            boolean gluttonyCursed = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CURSEGLUTTONY.get(), player) > 0;
//            if (!gluttonyCursed) return;
//            //10% chance to trigger
//            if (Math.random() > 0.10) return;
//
//            FoodData foodData = player.getFoodData();
//            foodData.setSaturation(0);//foodData.getSaturationLevel()/2);
//            System.out.println(foodData.getFoodLevel());
//            System.out.println(foodData.getSaturationLevel());
//        }
//    }
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