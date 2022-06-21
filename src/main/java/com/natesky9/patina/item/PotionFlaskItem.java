package com.natesky9.patina.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
    private int uses;
    private Potion potion;

    public PotionFlaskItem(Properties pProperties) {
        super(pProperties);
        this.uses = 0;
        this.capacity = 6;
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return this.uses / this.capacity;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        if (PotionUtils.getPotion(pStack).getEffects().isEmpty()) return 0;
        return PotionUtils.getColor(this.potion);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            if (pSlotId == Inventory.SLOT_OFFHAND) {
                ItemStack otherItem = player.getInventory().getSelected();
                Potion getPotion = PotionUtils.getPotion(otherItem);
                if (getPotion == this.potion || this.potion == null) {
                    this.uses++;
                    player.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GLASS_BOTTLE));
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //crouch to fill?
        if (this.uses > 0)
            return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
        else return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        this.uses = Math.min(this.uses + 1, this.capacity);
        this.potion = Potions.SWIFTNESS;

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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TextComponent("sips: " + this.uses));
        PotionUtils.addPotionTooltip(pStack,pTooltipComponents,0);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        potion.getEffects().forEach(pLivingEntity::addEffect);

        //pLivingEntity.addEffect(instance);
        this.uses -= 1;
        if (this.uses == 0)
            this.potion = null;
        return pStack;
    }
}
