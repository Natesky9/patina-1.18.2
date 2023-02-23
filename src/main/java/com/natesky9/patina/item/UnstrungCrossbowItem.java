package com.natesky9.patina.item;

import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class UnstrungCrossbowItem extends ToolItem {
    public UnstrungCrossbowItem(Properties p_40850_) {
        super(p_40850_);
    }

    @Override
    RecipeType<? extends Recipe<SimpleContainer>> getRecipe() {
        return ModRecipeTypes.FINISHING_RECIPE_TYPE.get();
    }

    @Override
    SoundEvent getSound() {
        return SoundEvents.CROSSBOW_LOADING_START;
    }

    @Override
    boolean shrinkThis() {
        return true;
    }

    @Override
    boolean shrinkThat() {
        return true;
    }
    //

    @Override
    public Component getName(ItemStack pStack) {
        return super.getName(pStack);
    }
}
