package com.natesky9.patina.Enchantments;

import com.natesky9.patina.Patina;
import com.natesky9.patina.TriggeredEnchant;
import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.HashMap;
import java.util.Map;

public class RetributionEnchantment extends Enchantment implements TriggeredEnchant {
    ItemStack item = new ItemStack(Items.GOLDEN_SWORD);
    public RetributionEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    //retribution stores the premitigated damage done
    //and returns it upon attacking the source

    protected static Map<Player, Map<LivingEntity,Float>> retaliatemap = new HashMap<>(new HashMap<>());

    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingHurtEvent event)
    {
        DamageSource source = event.getSource();
        float amount = event.getAmount();
        LivingEntity target = event.getEntityLiving();

        if (!(source.getEntity() instanceof LivingEntity entity)) return;

        if (target instanceof ServerPlayer player)
        {
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WRATHBLESSING.get(), target) == 0) return;

        //if the player is attacking something
            if (!retaliatemap.containsKey(player)) return;
            //exit early if there's nothing stored
            if (Patina.enchantmentCooldowns.isOnCooldown(player,ModEnchantments.WRATHBLESSING.get())) return;
            //exit early if the enchant is on cooldown
            Patina.enchantmentCooldowns.addCooldown(player,ModEnchantments.WRATHBLESSING.get(),200);

            Map<LivingEntity, Float> retaliate = retaliatemap.get(player);

            if (retaliate.containsKey(target)) {
                float retaliationDamage = retaliate.get(target);
                System.out.println("damage: " + retaliationDamage);
                event.setAmount(event.getAmount() + retaliationDamage);
                Component message = new TextComponent("Released damage: " + retaliationDamage).withStyle(ChatFormatting.RED);
                player.displayClientMessage(message, true);
                retaliatemap.remove(player);

            }
        }
        if (entity instanceof ServerPlayer player)
        {
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WRATHBLESSING.get(), entity) == 0) return;

        //if the player is attacked

            retaliatemap.put(player, Map.of(entity, amount));
            entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200));
            Component message = new TextComponent("Stored damage: " + amount).withStyle(ChatFormatting.RED);
            player.displayClientMessage(message, true);

        }
    }
    //
    @Override
    public String getMessage() {
        return "You return the pain";
    }
    @Override
    public SoundEvent getSound() {
        return SoundEvents.PIGLIN_BRUTE_ANGRY;
    }
    @Override
    public SoundEvent getSound2() {
        return SoundEvents.RESPAWN_ANCHOR_SET_SPAWN;
    }
    @Override
    public ItemStack getItem() {
        return item;
    }
}
