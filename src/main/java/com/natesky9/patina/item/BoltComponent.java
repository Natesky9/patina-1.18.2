package com.natesky9.patina.item;

import com.natesky9.patina.PatinaArchery;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModRecipeTypes;
import com.natesky9.patina.recipe.FletchingRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

public class BoltComponent extends Item {
    public BoltComponent(Properties pProperties) {
        super(pProperties);
    }
    //----------//
    @Override
    public Component getName(ItemStack stack) {
        //replace the name with what's in the nbt
        CompoundTag tag = stack.getTag();
        if (tag == null) return super.getName(stack);
        if (tag.contains("metal"))
        {
            Item metal = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("metal")));
            return new TranslatableComponent("component.patina.bolt_blank_name",
                    metal.getName(new ItemStack(metal)).getString().replace(" Ingot",""));
        }
        if (tag.contains("gem"))
        {
            Item gem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("gem")));
            return new TranslatableComponent("component.patina.bolt_tips_name",
                    gem.getName(new ItemStack(gem)).getString());
        }
        return super.getName(stack);
    }

    public static int getColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("color");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 5;
    }

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

        SimpleContainer inventory = new SimpleContainer(hands);
        Optional<FletchingRecipe> match = pLevel.getRecipeManager().getRecipeFor(ModRecipeTypes.FLETCHING_RECIPE_TYPE.get(), inventory,pLevel);
        if (match.isPresent())
        {
            pLevel.playSound(null,player.blockPosition(),
                    SoundEvents.AMETHYST_BLOCK_STEP, SoundSource.PLAYERS,.1F,2F);
            //get the stack of the opposite hand
            ItemStack stack = player.getItemInHand(player.getUsedItemHand() == InteractionHand.MAIN_HAND ?
                    InteractionHand.OFF_HAND:InteractionHand.MAIN_HAND);
            ItemStack crafted = match.get().getResultItem();
            ItemEntity entity = new ItemEntity(pLevel,player.getX(),
                    player.getY()+player.getEyeHeight(),player.getZ(),crafted);
            entity.setPickUpDelay(40);
            entity.setDeltaMovement(player.getLookAngle());
            pLevel.addFreshEntity(entity);
            stack.shrink(1);
            pStack.shrink(1);
            return pStack;
        }

        return pStack;
    }
    //----------//
}
