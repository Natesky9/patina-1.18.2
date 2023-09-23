package com.natesky9.patina.Item.flasks;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
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

public class PotionFlaskItem extends PotionItem {
    public static final int drinking = 32;
    public PotionFlaskItem(Properties pProperties) {
        super(pProperties);
    }
    public static float percentFull(ItemStack stack)
    {//used for visual fill
        stack.getMaxDamage();
        return (float)getUses(stack)/stack.getMaxDamage();
    }
    public static int getUses(ItemStack stack)
    {
        return stack.getDamageValue();
        //return stack.getOrCreateTag().getInt("uses");
    }
    public static void setUses(ItemStack stack, int value)
    {
        stack.setDamageValue(value);
        //stack.getOrCreateTag().putInt("uses", value);
        if (getUses(stack) == 0)
        {PotionUtils.setPotion(stack,Potions.EMPTY);}
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        return getUses(stack) > 0 ? ItemUtils.startUsingInstantly(pLevel,pPlayer,pUsedHand) : InteractionResultHolder.fail(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (getUses(pStack) <= 0)
            return pStack;//check for empty
        apply(pLivingEntity,pStack);
        setUses(pStack,getUses(pStack)-1);

        if (pLivingEntity instanceof Player player)
            player.awardStat(Stats.ITEM_USED.get(this));

        return pStack;
    }
    public static void apply(LivingEntity player,ItemStack stack)
    {
        Potion potion = PotionUtils.getPotion(stack);

        potion.getEffects().forEach(
                (effectInstance) -> {if (effectInstance.getEffect().isInstantenous()){
                    effectInstance.getEffect().applyInstantenousEffect(player, player, player, effectInstance.getAmplifier(), 1.0D);
                } else{player.addEffect(new MobEffectInstance(effectInstance));}});
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        if (!(pAction == ClickAction.SECONDARY)) return false;
        if (!pOther.is(Items.POTION) && !(pOther.getItem() instanceof PotionFlaskItem)) return false;
        Potion potion = PotionUtils.getPotion(pStack);
        Potion other = PotionUtils.getPotion(pOther);
        if (getUses(pStack) >= pStack.getMaxDamage()) return false;
        if (potion == Potions.EMPTY || potion == other)
        {//if the flask is empty or it matches
            if (pOther.getItem() instanceof PotionFlaskItem)
            {//if it's our flask
                int current = getUses(pStack);
                int otherCurrent = getUses(pOther);
                int transfer = Math.min(pStack.getMaxDamage()-current,otherCurrent);
                PotionUtils.setPotion(pStack,PotionUtils.getPotion(pOther));
                setUses(pStack,current+transfer);
                setUses(pOther,otherCurrent-transfer);
                pPlayer.level.playSound(null,pPlayer, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS,1,1);
                return true;
            }
            if (pOther.getItem() instanceof PotionItem)
            {//if it's a vanilla potion
                PotionUtils.setPotion(pStack,other);
                setUses(pStack,getUses(pStack)+1);
                pAccess.set(new ItemStack(Items.GLASS_BOTTLE));
                pPlayer.level.playSound(null,pPlayer, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS,1,1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("sips: " + getUses(stack)));
        PotionUtils.addPotionTooltip(stack,pTooltipComponents,1);
    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this));
    }

    @Override
    public int getBarWidth(ItemStack pStack)
    {
        return (int)((getUses(pStack)/(float)pStack.getMaxDamage())*14);
    }

    @Override
    public int getBarColor(ItemStack pStack)
    {
        return PotionUtils.getColor(pStack);
    }

    @Override
    public int getUseDuration(ItemStack pStack)
    {
        return 24;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack)
    {
        return UseAnim.DRINK;
    }
}
