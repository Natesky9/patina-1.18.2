package com.natesky9.patina.item;

import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class ChiselItem extends ToolItem {
    public ChiselItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    @Override
    RecipeType<? extends Recipe<SimpleContainer>> getRecipe() {
        return ModRecipeTypes.CHISEL_RECIPE_TYPE.get();
    }

    @Override
    SoundEvent getSound() {
        return SoundEvents.ANCIENT_DEBRIS_BREAK;
    }

    @Override
    boolean shrinkThis() {
        return false;
    }

    @Override
    boolean shrinkThat() {
        return true;
    }
    //----------//
}
