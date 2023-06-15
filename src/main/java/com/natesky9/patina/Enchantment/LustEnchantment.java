package com.natesky9.patina.Enchantment;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
        if (!(event.getEntity() instanceof Player player)) return;
        //reduce damage by 1 heart
        float amount = event.getAmount();
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CURSELUST.get(), player) == 0) return;

        //10% chance to trigger
        if (Math.random() > 0.10) return;
        player.displayClientMessage(Component.translatable("patina.lust_trigger"),true);
        player.level.playSound(null,player.blockPosition(), SoundEvents.CHICKEN_HURT,
                SoundSource.PLAYERS,0.5F,0.5F);

        event.setAmount(amount-1F);
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
