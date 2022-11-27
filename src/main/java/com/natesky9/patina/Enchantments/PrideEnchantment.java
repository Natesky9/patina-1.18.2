package com.natesky9.patina.Enchantments;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class PrideEnchantment extends Enchantment {
    public PrideEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
    public static void doEffect(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        float amount = event.getAmount();
        DamageSource source = event.getSource();
        //ignore neutral sources of damage
        if (source == DamageSource.FALL || source == DamageSource.DROWN) return;

        //if pride exists, all armor takes extra durability
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.PRIDECURSE.get(), entity)>0)
        for (ItemStack stack: entity.getArmorSlots())
        {
            {
                stack.hurtAndBreak((int)amount,entity,(player) ->
                {player.broadcastBreakEvent(entity.getUsedItemHand());});
            }
        }
    }
    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_RED);
        //placeholder to test
    }
    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return ModEnchantments.deadlySins.contains(pOther);
    }
}
