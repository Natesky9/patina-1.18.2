package com.natesky9.patina.Item;

import com.natesky9.patina.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EssenceItem extends Item {
    public EssenceItem(Properties p_41383_) {
        super(p_41383_);
    }
    public static void create(LivingDropsEvent event)
    {
        //essence drop
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.experienceLevel < 15) return;
        if (!player.getInventory().hasAnyMatching(item -> item.is(ModItems.ESSENCE.get()))) return;

        int value = 0;
        for (int i = 0; i < player.experienceLevel-1; i++)
        {
            value += EssenceItem.valuePerLevel(i);
        }
        player.experienceLevel = 0;
        player.experienceProgress = 0;

        for (ItemEntity entity:event.getDrops())
        {
            ItemStack stack = entity.getItem();
            if (!stack.is(ModItems.ESSENCE.get())) continue;
            int oldValue = getValue(stack);
            setValue(stack,oldValue+value);

        }

        //ItemStack stack = new ItemStack(ModItems.ESSENCE.get());
        //setValue(stack, value);
        //setCrude(stack, true);
        //ItemEntity entity = player.spawnAtLocation(stack);
        //event.getDrops().add(entity);

    }
    public static boolean shouldCreate(LivingExperienceDropEvent event)
    {
        //15 levels
        if (!(event.getEntity() instanceof Player player)) return false;
        int value = 0;
        for (int i = 0; i < player.experienceLevel; i++)
        {
            value += EssenceItem.valuePerLevel(i);
        }
        //this fixes the "6 levels max" when dying
        event.setDroppedExperience(value);
        //only create essence after lv 15
        return player.getInventory().hasAnyMatching(item -> item.is(ModItems.ESSENCE.get()));
        //return player.experienceLevel >= 15;
    }
    public static int valuePerLevel(int level)
    {
        if (level > 30)
            return 112 + (level-30)*9;
        if (level > 15)
            return 37 + (level-15)*5;
        return 7+level*2;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack home, ItemStack away, Slot p_150894_, ClickAction p_150895_, Player player, SlotAccess access) {
        if (!away.is(this)) return false;
        if (isCrude(home) != isCrude(away)) return false;
        int first = getValue(home);
        int second = getValue(away);
        setValue(home, first + second);
        away.shrink(1);
        player.level().playSound(player,player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS,.5F,.5F);
        return true;
    }
    public static int getValue(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("value");
    }
    public static void setValue(ItemStack stack, int value)
    {
        stack.getOrCreateTag().putInt("value", value);
    }
    public static void setCrude(ItemStack stack, boolean crude)
    {
        stack.getOrCreateTag().putBoolean("crude", crude);
    }
    public static boolean isCrude(ItemStack stack)
    {
        return stack.getOrCreateTag().getBoolean("crude");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> components, TooltipFlag p_41424_) {
        int value = stack.getOrCreateTag().getInt("value");
        components.add(Component.literal("Value: " + value));
        if (isCrude(stack))
            components.add(Component.literal("a crude chunk of crystalline essence").withStyle(ChatFormatting.AQUA));
        else
            components.add(Component.literal("a refined crystal of essence").withStyle(ChatFormatting.AQUA));
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        setValue(stack,42);
        setCrude(stack,false);
        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        int value = getValue(stack);
        //100 is ?
        return 10;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level p_41410_, LivingEntity entity) {
        if (!(entity instanceof Player player)) return itemStack;
        int value = getValue(itemStack);
        player.giveExperiencePoints(value);
        player.displayClientMessage(Component.literal("consuming essence is for debug only"), true);
        return ItemStack.EMPTY;
    }
}
