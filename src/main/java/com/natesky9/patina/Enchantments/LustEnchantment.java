package com.natesky9.patina.Enchantments;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LustEnchantment extends Enchantment {
    public LustEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
    public static void doEffect(LivingHurtEvent event)
    {
        //reduce damage by 1 heart
        LivingEntity entity = event.getEntityLiving();
        float amount = event.getAmount();
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LUSTCURSE.get(), entity) > 0)
        {
            event.setAmount(amount-1F);
        }
    }

}
