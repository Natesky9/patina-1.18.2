package com.natesky9.patina.Enchantment;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.VanillaGameEvent;

public class BrimfulEnchantment extends Enchantment {
    static final MobEffectInstance brimful = new MobEffectInstance(MobEffects.SATURATION,100,0);
    public BrimfulEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    //gluttony blessing sets the players saturation to max upon eating
    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(VanillaGameEvent event)
    {
        if (event.getCause() instanceof Player player)
        {
            //10% chance to trigger
            if (Math.random() > 0.1) return;
            boolean gluttonyBlessed = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLESSINGGLUTTONY.get(), player) > 0;
            if (!gluttonyBlessed) return;

            player.displayClientMessage(Component.translatable("patina.brimful_trigger"),true);
            player.level.playSound(null,player.blockPosition(),SoundEvents.PIG_AMBIENT,
                    SoundSource.PLAYERS,0.5F,0.5F);

            player.addEffect(brimful);
            //FoodData foodData = player.getFoodData();
            //foodData.setSaturation(20);
        }
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
