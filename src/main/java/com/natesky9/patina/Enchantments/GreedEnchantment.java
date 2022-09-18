package com.natesky9.patina.Enchantments;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.Collection;
import java.util.Optional;

public class GreedEnchantment extends Enchantment {
    public GreedEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
    public static void doEffect(LivingDropsEvent event)
    {
        DamageSource source = event.getSource();
        if (!(source instanceof EntityDamageSource entitySource)) return;
        if (entitySource.getEntity() instanceof Player player)
        {
            boolean greedCursed = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GREEDCURSE.get(), player) > 0;
            Collection<ItemEntity> collection = event.getDrops();
            Optional<ItemEntity> first = collection.stream().findFirst();
            if (first.isPresent() && greedCursed)
            collection.remove(first);
        }
    }
}
