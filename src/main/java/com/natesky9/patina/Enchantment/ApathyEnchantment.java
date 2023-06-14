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
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class ApathyEnchantment extends Enchantment {
    public ApathyEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);

    }
    public static void doEffect(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Player player)) return;
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLESSINGSLOTH.get(), player) == 0) return;

        //10% chance to trigger
        if (Math.random() > 0.1) return;
        //do the stuff here
        player.displayClientMessage(Component.translatable("patina.apathy_trigger"),true);
        entity.level.playSound(null,entity.blockPosition(),SoundEvents.SHIELD_BLOCK,
                SoundSource.PLAYERS,0.5F,0.5F);
        //cancel the damage like twitter cancels celebrities
        event.setCanceled(true);
    }
    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }
}
