package com.natesky9.patina.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.jetbrains.annotations.Nullable;
import org.jline.utils.Colors;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.Collection;
import java.util.List;

public class SoupBottleItem extends Item
{
    private int uses;
    private final int capacity;
    private FoodProperties food;
    public SoupBottleItem(Properties pProperties)
    {
        super(pProperties);
        this.capacity = 12;
    }
    public int getUses(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("uses");
    }
    public void setUses(ItemStack stack, int amount)
    {
        stack.getOrCreateTag().putInt("uses",amount);
    }
    public FoodProperties getFood(ItemStack stack)
    {
        String name = stack.getOrCreateTag().getString("food");
        if (name.equals("")) return null;
        int hunger = stack.getOrCreateTag().getInt("hunger");
        float saturation = stack.getOrCreateTag().getFloat("saturation");
        FoodProperties.Builder getfood = new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation);
        //add the effects here
        Collection<MobEffectInstance> potions = PotionUtils.getCustomEffects(stack);

        //
        //if (!potions.isEmpty()) {
        //    for (MobEffectInstance mobeffectinstance : potions) {
        //        getfood.effect(new MobEffectInstance(mobeffectinstance),1);
        //    }
        //}
        return getfood.build();
    }
    public void setFood(ItemStack stack, Item bowl, FoodProperties food)
    {
        if (bowl == null || food == null)
        {
            stack.getOrCreateTag().putString("food","empty");
            stack.getOrCreateTag().putInt("hunger",0);
            stack.getOrCreateTag().putFloat("saturation",0);
            return;
        }
        String name = bowl.toString();

        stack.getOrCreateTag().putString("food",name);
        stack.getOrCreateTag().putInt("hunger",food.getNutrition());
        stack.getOrCreateTag().putFloat("saturation",food.getSaturationModifier());

        //Collection<MobEffectInstance> potions = food.getEffects();
        //PotionUtils.setCustomEffects(stack,potions);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<net.minecraft.network.chat.Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TextComponent("sips: " + getUses(stack)));
        String food = stack.getOrCreateTag().getString("food");
        pTooltipComponents.add(new TextComponent("contents: " + food));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof ServerPlayer player) {
            if (!stack.equals(((Player) pEntity).getItemBySlot(EquipmentSlot.OFFHAND)))return;
            ItemStack otherItem = player.getInventory().getSelected();
            if (otherItem.isEmpty() || getUses(stack) == this.capacity) return;
            boolean is_sus = otherItem.getItem() instanceof SuspiciousStewItem;
            boolean is_bowl = otherItem.getItem() instanceof BowlFoodItem;
            if (!is_bowl && !is_sus) return;

            FoodProperties otherFood = otherItem.getFoodProperties(null);
            if (otherFood == null) return;
            String food1 = stack.getOrCreateTag().getString("food");
            String food2 = otherItem.getItem().toString();

            if (getUses(stack) == 0 || food1 == food2) {
                setUses(stack,getUses(stack)+1);
                setFood(stack, otherItem.getItem(), otherFood);
                player.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOWL));
            }

        }
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int width = (int)((float)getUses(stack) / (float)this.capacity*14);
        return width;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        String name = pStack.getOrCreateTag().getString("food");
        return switch (name) {
            case "mushroom_stew" -> Mth.color(205,140,111);
            case "beetroot_soup" -> Mth.color(132,22,13);
            case "rabbit_stew" -> Mth.color(226,156,74);
            case "suspicious_stew" -> Mth.color(168,212,117);
            default -> Mth.color(255,0,255);
        };
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (getUses(stack) > 0)
        return ItemUtils.startUsingInstantly(pLevel,pPlayer,pUsedHand);
        else return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        setUses(stack,getUses(stack)+1);
        setFood(stack, Items.MUSHROOM_STEW, Foods.MUSHROOM_STEW);

        return super.onDroppedByPlayer(stack, player);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 10;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level pLevel, LivingEntity pLivingEntity)
    {
        FoodProperties food = getFood(stack);
        if (pLivingEntity instanceof Player player)
            player.getFoodData().eat(food.getNutrition(),food.getSaturationModifier());
        setUses(stack,getUses(stack)-1);
        if (getUses(stack) == 0)
            setFood(stack,null,null);
        return stack;
    }
}
