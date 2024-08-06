//package com.natesky9.patina.Enchantment;
//
//import com.natesky9.patina.Enchantment.Util.ModEnchantmentUtil;
//import com.natesky9.patina.init.ModEnchantments;
//import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.Component;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraftforge.event.entity.living.LivingDamageEvent;
//
//public class PrideEnchantment extends Enchantment {
//    public PrideEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
//        super(pRarity, pCategory, pApplicableSlots);
//    }
//
//    @Override
//    public boolean isCurse() {
//        return true;
//    }
//    public static void doEffect(LivingDamageEvent event)
//    {
//        LivingEntity entity = event.getEntity();
//        if (!(event.getEntity() instanceof Player player)) return;
//        float amount = event.getAmount();
//        DamageSource source = event.getSource();
//        //ignore neutral sources of damage
//        if (source == entity.damageSources().fall() || source == entity.damageSources().drown()) return;
//
//        //if pride exists, all armor takes extra durability
//        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CURSEPRIDE.get(), entity) == 0) return;
//
//        //10% chance to trigger
//        if (Math.random() > 0.10) return;
//        player.displayClientMessage(Component.translatable("patina.pride_trigger"),true);
//        player.level().playSound(null,player.blockPosition(), SoundEvents.CHICKEN_HURT,
//                SoundSource.PLAYERS,0.5F,0.5F);
//
//        for (ItemStack stack: entity.getArmorSlots())
//        {
//            {//does this work?
//                stack.hurtAndBreak((int)amount,entity,(target) ->
//                {target.broadcastBreakEvent(entity.getUsedItemHand());});
//            }
//        }
//    }
//    @Override
//    public Component getFullname(int pLevel) {
//        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_RED);
//        //placeholder to test
//    }
//    @Override
//    protected boolean checkCompatibility(Enchantment pOther) {
//        return ModEnchantmentUtil.sins.contains(pOther);
//    }
//}
//