package com.natesky9.patina.item;

import com.natesky9.patina.Incursion;
import com.natesky9.patina.IncursionManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
            BlockPos pos = pPlayer.blockPosition();
            //check if we're already in an incursion
            boolean within = manager.isWithinIncursion(pos);
            boolean creative = pPlayer.isCreative();
            if (within && creative)
            {
                Incursion incursion = manager.getIncursion(level,pos);
                incursion.success();
                return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
            }
            boolean existing = manager.isNearbyIncursion(pos,100);
            if (existing)
            {
                Incursion incursion = manager.findNearbyIncursion(level,pos,100);
                System.out.println("there's already an incursion nearby");
                pPlayer.displayClientMessage(new TextComponent("There's an incursion nearby!"),true);
                level.playSound(null,pPlayer, SoundEvents.AXOLOTL_HURT, SoundSource.PLAYERS,1,1);
                return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
            }


            //create an incursion if the time is right
            boolean night = Incursion.night(level.getDayTime());
            if (!night)
                pPlayer.displayClientMessage(new TextComponent("has to be night to create incursions"),true);
            boolean moon = level.getMoonPhase() == 0;
            if (!moon)
                pPlayer.displayClientMessage(new TextComponent("has to be a full moon to create incursions"),true);
            if (night && moon)
            {
                level.playSound(null,pPlayer, SoundEvents.ANVIL_USE, SoundSource.PLAYERS,.5F,1);
                Incursion incursion = manager.createIncursion(level, pos);
                pPlayer.displayClientMessage(new TextComponent("Incursion created!"),true);
            }

        }
        return super.use(level, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!(pLevel instanceof ServerLevel)) return;
        IncursionManager manager = IncursionManager.get(pLevel);
        if (manager.isWithinIncursion(pEntity.blockPosition()))
        {
            pStack.getOrCreateTag().putBoolean("active",true);
        }
        else pStack.getOrCreateTag().remove("active");
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        //shiny if in a raid
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains("active"))
        {
            return tag.getBoolean("active");
        }
        return false;
    }
}
