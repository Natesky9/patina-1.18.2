package com.natesky9.patina.Item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PotionFlaskItem extends Item {
    static final int capacity = 3;
    public PotionFlaskItem(Properties pProperties) {
        super(pProperties);
    }
    public static float percentFull(ItemStack stack)
    {//used for visual fill
        return (float)getUses(stack)/capacity;
    }
    public static int getUses(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("uses");
    }
    public void setUses(ItemStack stack, int value)
    {
        stack.getOrCreateTag().putInt("uses", value);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        return getUses(stack) > 0 ? ItemUtils.startUsingInstantly(pLevel,pPlayer,pUsedHand) : InteractionResultHolder.fail(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        Potion potion = PotionUtils.getPotion(pStack);

        potion.getEffects().forEach(
                (effectInstance) -> {if (effectInstance.getEffect().isInstantenous()){
            effectInstance.getEffect().applyInstantenousEffect(pLivingEntity, pLivingEntity, pLivingEntity, effectInstance.getAmplifier(), 1.0D);
        } else{pLivingEntity.addEffect(new MobEffectInstance(effectInstance));}});
        setUses(pStack,getUses(pStack)-1);
         if (getUses(pStack) == 0)
         {PotionUtils.setPotion(pStack,Potions.EMPTY);}
        return pStack;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        if (!(pAction == ClickAction.SECONDARY)) return false;
        if (!pOther.is(Items.POTION)) return false;
        Potion potion = PotionUtils.getPotion(pStack);
        Potion other = PotionUtils.getPotion(pOther);
        if (getUses(pStack) >= capacity) return false;
        if (potion == Potions.EMPTY || potion == other)
        {
            PotionUtils.setPotion(pStack,other);
            setUses(pStack,getUses(pStack)+1);
            pAccess.set(new ItemStack(Items.GLASS_BOTTLE));
            return true;
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("sips: " + getUses(stack)));
        PotionUtils.addPotionTooltip(stack,pTooltipComponents,1);
    }

    @Override
    public int getBarWidth(ItemStack pStack)
    {
        return (int)((getUses(pStack)/(float)this.capacity)*14);
    }

    @Override
    public int getBarColor(ItemStack pStack)
    {
        return PotionUtils.getColor(pStack);
    }

    @Override
    public int getUseDuration(ItemStack pStack)
    {
        return 20;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack)
    {
        return UseAnim.DRINK;
    }
}
