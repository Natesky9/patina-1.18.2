package com.natesky9.patina.Enchantments;

import com.natesky9.patina.EnchantCooldowns;
import com.natesky9.patina.Patina;
import com.natesky9.patina.TriggeredEnchant;
import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CoercionEnchantment extends Enchantment implements TriggeredEnchant {
    ItemStack item = new ItemStack(Items.SADDLE);
    static final MobEffectInstance suck = new MobEffectInstance(MobEffects.REGENERATION,3,2,true,false);
    public CoercionEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingDamageEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LUSTBLESSING.get(), player) == 0) return;
        EnchantCooldowns cooldowns = Patina.enchantmentCooldowns;
        if (cooldowns.isOnCooldown(player,ModEnchantments.LUSTBLESSING.get())) return;
        LivingEntity target = event.getEntityLiving();
        if (player.getLastHurtMob() != target) return;

        cooldowns.addCooldown(player, ModEnchantments.LUSTBLESSING.get(),200);

        target.hurt(DamageSource.MAGIC,1);
        player.addEffect(suck);
    }
    //
    @Override
    public String getMessage() {
        return "You siphon some vitality";
    }
    @Override
    public SoundEvent getSound() {
        return SoundEvents.BREWING_STAND_BREW;
    }
    @Override
    public SoundEvent getSound2() {
        return SoundEvents.ZOMBIE_VILLAGER_CONVERTED;
    }
    @Override
    public ItemStack getItem() {
        return item;
    }
}
