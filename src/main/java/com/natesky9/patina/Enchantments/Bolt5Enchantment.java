package com.natesky9.patina.Enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Bolt5Enchantment extends Enchantment {
    public Bolt5Enchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (pAttacker instanceof ServerPlayer player)
        {
            BlockPos pos = pTarget.blockPosition();
            ServerLevel level = (ServerLevel) player.level;
            Bee bee = (Bee) EntityType.BEE.spawn(level,null,player,pos, MobSpawnType.TRIGGERED,true,true);
            bee.setPersistentAngerTarget(pTarget.getUUID());
        }
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}
