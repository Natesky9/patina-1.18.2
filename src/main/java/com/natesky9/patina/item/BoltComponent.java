package com.natesky9.patina.item;

import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.recipe.ToolRecipe;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.Optional;

public class BoltComponent extends Item {
    public BoltComponent(Properties pProperties) {
        super(pProperties);
    }
    //----------//

    @Override
    public Component getName(ItemStack pStack) {
        CompoundTag tag = pStack.getTag();
        if (tag == null) return super.getName(pStack);
        if (pStack.is(ModItems.BOLT_TIPS.get()))
        {
            return new TextComponent(tag.getString("gem") + " bolt tips");
        }
        if (pStack.is(ModItems.UNFINISHED_BOLTS.get()))
        {
            return new TextComponent(tag.getString("metal") + " unfinished bolts");
        }
        return new TextComponent("Blank Item, craft instead!");
    }

    public static int getColor(ItemStack stack)
    {
        String value = "";
        if (stack.is(ModItems.BOLT_TIPS.get()))
            value = stack.getOrCreateTag().getString("gem");
        if (stack.is(ModItems.UNFINISHED_BOLTS.get()))
            value = stack.getOrCreateTag().getString("metal");
        Item getItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(value));
        if (getItem == Items.EMERALD) return Color.green.getRGB();
        if (getItem == Items.DIAMOND) return Color.CYAN.getRGB();
        if (getItem == ModItems.RUBY.get()) return Color.RED.getRGB();

        if (getItem == Items.IRON_INGOT) return Color.gray.getRGB();
        if (getItem == Items.GOLD_INGOT) return Color.yellow.getRGB();
        if (getItem == Items.COPPER_INGOT) return Color.green.getRGB();
        if (getItem == ModItems.INGOT_1.get()) return Color.ORANGE.getRGB();
        if (getItem == ModItems.INGOT_2.get()) return Color.BLUE.getRGB();
        return Color.MAGENTA.getRGB();
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
        Optional<ToolRecipe> match = pLevel.getRecipeManager().getRecipeFor(ToolRecipe.Type.INSTANCE,inventory,pLevel);
        System.out.println(pLevel.getRecipeManager().getAllRecipesFor(ToolRecipe.Type.INSTANCE));
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
