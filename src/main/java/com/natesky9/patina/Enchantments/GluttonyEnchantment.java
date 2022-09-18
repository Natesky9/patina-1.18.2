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

public class GluttonyEnchantment extends Enchantment {
    public GluttonyEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
    public static void doEffect(TickEvent.PlayerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START) return;
        Player player = event.player;
        boolean gluttonyCursed = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GLUTTONYCURSE.get(), player) > 0;
        if (!gluttonyCursed) return;

        FoodData data = player.getFoodData();
        if (data.getFoodLevel() == data.getLastFoodLevel()) return;
        int difference = Math.max(data.getLastFoodLevel() - data.getFoodLevel(),0);
        for (ItemStack itemstack: player.getArmorSlots())
        {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.GLUTTONYCURSE.get(), itemstack)>0)
                player.causeFoodExhaustion(difference*4);
        }
    }
}
