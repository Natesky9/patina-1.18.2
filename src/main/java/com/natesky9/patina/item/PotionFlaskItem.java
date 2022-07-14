package com.natesky9.patina.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PotionFlaskItem extends Item {
    private final int capacity;

    public PotionFlaskItem(Properties pProperties) {
        super(pProperties);
        this.capacity = 6;
    }

    //setter and getter for uses, potion is done via PotionUtils
    public int getUses(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("uses");
    }
    public void setUses(ItemStack stack,int value)
    {
        stack.getOrCreateTag().putInt("uses",value);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        //durability bar is 14 pixels wide
        return (int)((getUses(pStack) / (float)this.capacity) * 14);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        Potion potion = PotionUtils.getPotion(pStack);
        if (potion.getEffects().isEmpty()) return 0;
        return PotionUtils.getColor(potion);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof ServerPlayer player) {
            if (!stack.equals(((Player) pEntity).getItemBySlot(EquipmentSlot.OFFHAND)))return;
            ItemStack otherItem = player.getInventory().getSelected();
            if (otherItem.isEmpty() || !otherItem.is(Items.POTION) || getUses(stack) == this.capacity) return;

            Potion potion = PotionUtils.getPotion(stack);
            Potion getPotion = PotionUtils.getPotion(otherItem);
            if (getPotion == potion || potion == Potions.EMPTY) {
                setUses(stack,getUses(stack)+1);
                PotionUtils.setPotion(stack,getPotion);
                player.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GLASS_BOTTLE));
            }

        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //crouch to fill?
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (getUses(stack) > 0)
            return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
        else return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 24;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TextComponent("sips: " + getUses(stack)));
        PotionUtils.addPotionTooltip(stack,pTooltipComponents,1);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLevel.isClientSide) return stack;
        Potion potion = PotionUtils.getPotion(stack);
        potion.getEffects().forEach(pLivingEntity::addEffect);

        //pLivingEntity.addEffect(instance);
        setUses(stack,getUses(stack)-1);
        if (getUses(stack) == 0)
        {
        PotionUtils.setPotion(stack,Potions.EMPTY);
        }
        return stack;
    }
}
