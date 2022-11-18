package com.natesky9.patina.Enchantments;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public class SlothEnchantment extends Enchantment {
    public SlothEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
    public static void doEffect(LivingHealEvent event)
    {
        Entity entity = event.getEntity();
        float amount = event.getAmount();

        if (entity instanceof Player player)
        {
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SLOTHCURSE.get(), player) == 0) return;
            player.displayClientMessage(new TextComponent("Curse of Sloth reduces your natural regeneration"),true);
            if (amount <= 1)
                event.setAmount(amount/2);
            else
                event.setAmount(amount-1);
        }
    }
}
