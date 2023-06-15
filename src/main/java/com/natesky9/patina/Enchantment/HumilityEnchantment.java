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

public class HumilityEnchantment extends Enchantment {
    public HumilityEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingDamageEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLESSINGPRIDE.get(), player) == 0) return;

        //10% chance to trigger
        if (Math.random() > 0.10) return;
        player.displayClientMessage(Component.translatable("patina.humility_trigger"),true);
        player.level.playSound(null,player.blockPosition(),SoundEvents.ENDER_DRAGON_GROWL,
                SoundSource.PLAYERS,0.5F,0.5F);

        LivingEntity entity = event.getEntity();
        float bossHealth = entity.getMaxHealth();
        if (bossHealth/2 < player.getMaxHealth()) return;

        event.setAmount(event.getAmount() + bossHealth/6);
    }
    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }
}
