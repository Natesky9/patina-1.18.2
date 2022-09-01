package com.natesky9.patina.item;

import com.natesky9.patina.Incursion;
import com.natesky9.patina.IncursionManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DebugRaidItem extends Item {
    public DebugRaidItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand pUsedHand) {
        if (level instanceof ServerLevel serverLevel)
        {
            IncursionManager manager = IncursionManager.get(level);
            //check if we're already in an incursion
            boolean existing = manager.within(pPlayer.blockPosition());
            if (existing)
            {
                Incursion incursion = manager.find(level,pPlayer.blockPosition());
                incursion.rewardPlayers();
                System.out.println("in incursion, rewarding!");
                return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
            }


            //otherwise, create one
            boolean night = Incursion.night((int)level.getDayTime()%24000);
            if (!night)
            System.out.println("has to be night");
            boolean moon = level.getMoonPhase() == 0;
            if (!moon)
            System.out.println("has to be full moon");
            if (night && moon)
            {
                Incursion incursion = manager.createIncursion(pPlayer);
            }

        }
        return super.use(level, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!(pLevel instanceof ServerLevel)) return;
        IncursionManager manager = IncursionManager.get(pLevel);
        if (manager.within(pEntity.blockPosition()))
        {
            pStack.getOrCreateTag().putBoolean("active",true);
        }
        else pStack.getOrCreateTag().remove("active");
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains("active"))
        {
            return tag.getBoolean("active");
        }
        return false;
    }
}
