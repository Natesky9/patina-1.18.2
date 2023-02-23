package com.natesky9.patina.item;

import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class EnchantOrbItem extends ToolItem{
    public EnchantOrbItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    RecipeType<? extends Recipe<SimpleContainer>> getRecipe() {
        return ModRecipeTypes.ENCHANTING_RECIPE_TYPE.get();
    }

    @Override
    SoundEvent getSound() {
        return SoundEvents.ENCHANTMENT_TABLE_USE;
    }

    @Override
    boolean shrinkThis() {
        return false;
    }

    @Override
    boolean shrinkThat() {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }
}
