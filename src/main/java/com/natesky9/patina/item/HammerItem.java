package com.natesky9.patina.item;

import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class HammerItem extends ToolItem {
    public HammerItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    RecipeType<? extends Recipe<SimpleContainer>> getRecipe() {
        return ModRecipeTypes.HAMMER_RECIPE_TYPE.get();
    }

    @Override
    SoundEvent getSound() {
        return SoundEvents.ANVIL_USE;
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
