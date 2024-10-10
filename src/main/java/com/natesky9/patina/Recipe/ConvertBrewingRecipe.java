package com.natesky9.patina.Recipe;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class ConvertBrewingRecipe implements IBrewingRecipe
{
    private final Holder<Potion> input;
    private final Item catalyst;
    private final Item output;

    public ConvertBrewingRecipe(Holder<Potion> input, Item catalyst, Item output)
    {
        this.input = input;
        this.catalyst = catalyst;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input)
    {
        if (!input.has(DataComponents.POTION_CONTENTS)) return false;
        return input.get(DataComponents.POTION_CONTENTS).potion().get().equals(this.input);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient)
    {
        return ingredient.is(catalyst);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        if(!this.isInput(input) || !this.isIngredient(ingredient))
        {
            return ItemStack.EMPTY;
        }
        return new ItemStack(output);
    }
}
