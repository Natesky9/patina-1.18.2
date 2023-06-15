package com.natesky9.patina.Enchantment;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
            //10% chance to trigger
            if (Math.random() > 0.1) return;
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CURSESLOTH.get(), player) == 0) return;
            player.level.playSound(null,player.blockPosition(), SoundEvents.WOOL_PLACE,
                    SoundSource.PLAYERS,0.5F,0.5F);
            player.displayClientMessage(Component.translatable("patina.sloth_trigger"),true);
            //heal half for low amounts
            if (amount <= 1)
                event.setAmount(amount/2);
            else
                //heal 1 less for everything else
                event.setAmount(amount-1);
        }
    }
    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_RED);
        //placeholder to test
    }
    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }
}
