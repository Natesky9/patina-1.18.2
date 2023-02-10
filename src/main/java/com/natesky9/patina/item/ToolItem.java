package com.natesky9.patina.item;

import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
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
    protected int useDuration;
    public ToolItem(Properties pProperties)
    {
        super(pProperties);
    }
    //----------//
    @Override
    public int getUseDuration(ItemStack pStack)
    {return useDuration;}

    abstract RecipeType<? extends Recipe<SimpleContainer>> getRecipe();
    abstract SoundEvent getSound();
    //----------//

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.EAT;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(itemstack);
    }
    //----------//
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity)
    {
        if (!(pLivingEntity instanceof ServerPlayer player)) return pStack;
        ItemStack left = player.getItemInHand(InteractionHand.OFF_HAND);
        ItemStack right = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (left.isEmpty() || right.isEmpty()) return pStack;
        boolean offhand = player.getUsedItemHand() == InteractionHand.OFF_HAND;
        ItemStack[] hands = {offhand ? left:right,
                            offhand ? right:left};

        SimpleContainer container = new SimpleContainer(hands);
        Optional<? extends Recipe<?>> match = pLevel.getRecipeManager().getRecipeFor(getRecipe(), container,pLevel);
        //System.out.println(pLevel.getRecipeManager().getAllRecipesFor(ModRecipeTypes.TOOL_RECIPE_TYPE.get()));
        if (match.isPresent())
        {
            pLevel.playSound(null,player.blockPosition(), getSound(), SoundSource.PLAYERS,.1F,2F);
            //get the stack of the opposite hand
            ItemStack stack = player.getItemInHand(player.getUsedItemHand() == InteractionHand.MAIN_HAND ?
                    InteractionHand.OFF_HAND:InteractionHand.MAIN_HAND);
            stack.shrink(1);
            ItemStack crafted = match.get().getResultItem();
            ItemEntity entity = new ItemEntity(pLevel,player.getX(),
                    player.getY()+player.getEyeHeight(),player.getZ(),crafted);
            entity.setPickUpDelay(40);
            entity.setDeltaMovement(player.getLookAngle());
            pLevel.addFreshEntity(entity);
            return pStack;
        }
        player.displayClientMessage(new TextComponent("There doesn't seem to be a recipe"),true);
        return pStack;
    }
}
