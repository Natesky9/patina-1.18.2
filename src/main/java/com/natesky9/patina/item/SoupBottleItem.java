package com.natesky9.patina.item;

import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.jline.utils.Colors;

import java.awt.*;

public class SoupBottleItem extends Item
{
    private int uses;
    private final int capacity;
    private FoodProperties food;
    public SoupBottleItem(Properties pProperties)
    {
        super(pProperties);
        this.uses = 0;
        this.capacity = 12;
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
        return Color.HSBtoRGB(23,41,80);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (this.uses > 0)
        return ItemUtils.startUsingInstantly(pLevel,pPlayer,pUsedHand);
        else return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        this.uses = Math.max(this.uses+1,this.capacity);
        this.food = Foods.MUSHROOM_STEW;

        return super.onDroppedByPlayer(item, player);
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
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity)
    {
        if (pLivingEntity instanceof Player player)
            player.getFoodData().eat(food.getNutrition(),food.getSaturationModifier());
        this.uses -= 1;
        if (this.uses == 0)
            this.food = null;
        return pStack;
    }
}
