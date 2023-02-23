package com.natesky9.patina.item;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public abstract class ToolItem extends Item {
    public ToolItem(Properties pProperties)
    {
        super(pProperties);
    }
    //----------//
    @Override
    public int getUseDuration(ItemStack pStack)
    {return 2;}

    abstract RecipeType<? extends Recipe<SimpleContainer>> getRecipe();
    abstract SoundEvent getSound();
    abstract boolean shrinkThis();
    abstract boolean shrinkThat();
    //----------//

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        SimpleContainer container = makeHands(pPlayer, pUsedHand);
        if (matchRecipe(container,pLevel).isPresent())
        {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(itemstack);
        }
        return InteractionResultHolder.pass(itemstack);
    }
    //----------//
    Optional<? extends Recipe<SimpleContainer>> matchRecipe(SimpleContainer container, Level level)
    {
        return level.getRecipeManager().getRecipeFor(getRecipe(), container,level);
    }
    SimpleContainer makeHands(Player player, InteractionHand hand)
    {
        //make the container with tool in 0 and item in 1
        ItemStack left = player.getItemInHand(InteractionHand.OFF_HAND);
        ItemStack right = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (left.isEmpty() || right.isEmpty()) return new SimpleContainer();
        boolean offhand = hand == InteractionHand.OFF_HAND;
        ItemStack[] hands = {offhand ? left:right,
                offhand ? right:left};

        return new SimpleContainer(hands);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity)
    {
        if (!(pLivingEntity instanceof ServerPlayer player)) return pStack;
        SimpleContainer container = makeHands(player,player.getUsedItemHand());

        Optional<? extends Recipe<? extends Container>> match = matchRecipe(container,pLevel);
        if (match.isPresent())
        {
            pLevel.playSound(null,player.blockPosition(), getSound(), SoundSource.PLAYERS,.5F,2F);
            //get the stack of the opposite hand
            ItemStack mainHand = player.getItemInHand(player.getUsedItemHand());
            ItemStack offHand = player.getItemInHand(player.getUsedItemHand() == InteractionHand.MAIN_HAND ?
                    InteractionHand.OFF_HAND:InteractionHand.MAIN_HAND);
            if (shrinkThis())
            {
                mainHand.shrink(1);
            }
            if (shrinkThat())
            {
                offHand.shrink(1);
            }

            ItemStack crafted = match.get().getResultItem();
            ItemEntity entity = new ItemEntity(pLevel,player.getX(),
                    player.getY()+player.getEyeHeight(),player.getZ(),crafted);
            entity.setPickUpDelay(4);
            entity.setDeltaMovement(player.getLookAngle().scale(.1));
            pLevel.addFreshEntity(entity);
            return pStack;
        }
        player.displayClientMessage(new TextComponent("There doesn't seem to be a recipe"),true);
        return pStack;
    }
}
