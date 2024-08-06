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
//import net.minecraft.world.entity.item.ItemEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraftforge.event.entity.living.LivingDropsEvent;
//
//import java.util.Collection;
//import java.util.Optional;
//
//public class GreedEnchantment extends Enchantment {
//    public GreedEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
//        super(pRarity, pCategory, pApplicableSlots);
//    }
//
//    @Override
//    public boolean isCurse() {
//        return true;
//    }
//    public static void doEffect(LivingDropsEvent event)
//    {
//        DamageSource source = event.getSource();
//        if (!(source.getEntity() instanceof Player player)) return;
//        {
//            if (!(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CURSEGREED.get(), player) > 0)) return;
//
//            //10% chance to trigger
//            if (Math.random() > 0.1) return;
//            player.displayClientMessage(Component.translatable("patina.apathy_trigger"),true);
//            player.level().playSound(null,player.blockPosition(), SoundEvents.SHIELD_BLOCK,
//                    SoundSource.PLAYERS,0.5F,0.5F);
//
//            Collection<ItemEntity> collection = event.getDrops();
//            Optional<ItemEntity> first = collection.stream().findFirst();
//            if (first.isPresent())
//                collection.remove(first);
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