package com.natesky9.patina.Enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Curse extends Enchantment
{
    public Curse(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (pAttacker instanceof ServerPlayer player)
        {
            BlockPos pos = pTarget.blockPosition();
            ServerLevel level = (ServerLevel) player.level;
            EntityType.LIGHTNING_BOLT.spawn(level,null,player,pos, MobSpawnType.TRIGGERED,true,true);
        }
    }
}
