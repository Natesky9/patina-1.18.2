package com.natesky9.patina.Enchantments;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.VanillaGameEvent;

public class GluttonyEnchantment extends Enchantment {
    public GluttonyEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    //curse of gluttony halves the players saturation
    //whenever they try to eat anything

    @Override
    public boolean isCurse() {
        return true;
    }

    public static void doEffect(VanillaGameEvent event)
    {
        if (event.getCause() instanceof Player player)
        {
            boolean gluttonyCursed = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GLUTTONYCURSE.get(), player) > 0;
            if (!gluttonyCursed) return;

            FoodData foodData = player.getFoodData();
            foodData.setSaturation(foodData.getSaturationLevel()/2);
            System.out.println(foodData.getFoodLevel());
            System.out.println(foodData.getSaturationLevel());
        }
    }
}
