package com.natesky9.patina.Enchantment;

import com.natesky9.patina.Enchantment.Util.ModEnchantmentUtil;
import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

        LivingEntity entity = event.getEntity();
        for (ItemStack stack: entity.getArmorSlots())
        {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.CURSEWRATH.get(), stack)==0) return;

            event.setAmount(event.getAmount() + 1F);
            if (!(entity instanceof Player player)) return;
            //10% chance to trigger
            if (Math.random() > 0.10) return;
            player.displayClientMessage(Component.translatable("patina.wrath_trigger"), true);
            player.level.playSound(null,player.blockPosition(), SoundEvents.CHICKEN_HURT,
                    SoundSource.PLAYERS,0.5F,0.5F);
        }
    }
    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_RED);
        //placeholder to test
    }
    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return ModEnchantmentUtil.sins.contains(pOther);
    }
}
