package com.natesky9.patina.Enchantments;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class WrathEnchantment extends Enchantment {
    public WrathEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
    public static void doEffect(LivingDamageEvent event)
    {

        LivingEntity entity = event.getEntityLiving();
        for (ItemStack stack: entity.getArmorSlots())
        {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.WRATHCURSE.get(), stack)>0) {
                event.setAmount(event.getAmount() + 1F);
                if (entity instanceof Player player)
                    player.displayClientMessage(new TextComponent("The curse of wrath burns you"), true);
            }
        }
    }
}
