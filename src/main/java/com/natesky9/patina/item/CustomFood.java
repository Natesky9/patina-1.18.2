package com.natesky9.patina.item;

import com.natesky9.patina.init.ModFoods;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.AttributeSet;
import javax.swing.text.Style;
import java.util.List;

public class CustomFood extends Item {
    public CustomFood(Properties pProperties)
    {
        super(pProperties);
    }


    @Nullable
    @Override
    public FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        if (!stack.hasTag()) return ModFoods.TEST_FOOD;
        int hunger = stack.getOrCreateTag().getInt("hunger");
        float saturation = stack.getOrCreateTag().getFloat("saturation");
        return new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build();
    }
    public static void setFoodProperties(ItemStack stack, int hunger, float saturation)
    {
        stack.getOrCreateTag().putInt("hunger",hunger);
        stack.getOrCreateTag().putFloat("saturation",saturation);
    }
    public static void setIngredients(ItemStack stack, String food1, String food2, String food3, String food4)
    {
        stack.getOrCreateTag().putString("food 1",food1);
        stack.getOrCreateTag().putString("food 2",food2);
        stack.getOrCreateTag().putString("food 3",food3);
        stack.getOrCreateTag().putString("food 4",food4);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        int hunger = pStack.getOrCreateTag().getInt("hunger");
        float saturation = pStack.getOrCreateTag().getFloat("saturation");
        pTooltipComponents.add(new TextComponent("hunger: " + hunger).withStyle(ChatFormatting.RED));
        pTooltipComponents.add(new TextComponent("saturation: " + saturation).withStyle(ChatFormatting.YELLOW));
        String food1 = pStack.getOrCreateTag().getString("food 1");
        String food2 = pStack.getOrCreateTag().getString("food 2");
        String food3 = pStack.getOrCreateTag().getString("food 3");
        String food4 = pStack.getOrCreateTag().getString("food 4");

        if (food1.equals("")|| food2.equals("")||food3.equals("")||food4.equals("")) return;

        pTooltipComponents.add(new TextComponent("ingredients: "));
        pTooltipComponents.add(new TextComponent(food1).withStyle(ChatFormatting.AQUA));
        pTooltipComponents.add(new TextComponent(food2).withStyle(ChatFormatting.AQUA));
        pTooltipComponents.add(new TextComponent(food3).withStyle(ChatFormatting.AQUA));
        pTooltipComponents.add(new TextComponent(food4).withStyle(ChatFormatting.AQUA));
    }
}
