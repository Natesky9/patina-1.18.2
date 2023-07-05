package com.natesky9.patina.Enchantment;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class CoercionEnchantment extends Enchantment {
    static final MobEffectInstance suck = new MobEffectInstance(MobEffects.REGENERATION,3,2,true,false);
    public CoercionEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingDamageEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        //10% chance to trigger
        if (Math.random() > 0.10) return;
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLESSINGLUST.get(), player) == 0) return;

        player.displayClientMessage(Component.translatable("patina.coercion_trigger"),true);
        player.level.playSound(null,player.blockPosition(),SoundEvents.BREWING_STAND_BREW,
                SoundSource.PLAYERS,0.5F,0.5F);

        LivingEntity target = event.getEntity();
        if (player.getLastHurtMob() != target) return;


        target.hurt(target.damageSources().magic(),1);
        player.addEffect(suck);
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
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }
}
