package com.natesky9.patina.item;

import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class KnifeItem extends ToolItem {
    public KnifeItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    RecipeType<? extends Recipe<SimpleContainer>> getRecipe() {
        return ModRecipeTypes.KNIFE_RECIPE_TYPE.get();
    }

    @Override
    SoundEvent getSound() {
        return SoundEvents.SHEEP_SHEAR;
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
