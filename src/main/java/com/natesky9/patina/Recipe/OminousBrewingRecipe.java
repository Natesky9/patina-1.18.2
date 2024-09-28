package com.natesky9.patina.Recipe;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import java.util.Objects;

public class OminousBrewingRecipe implements IBrewingRecipe
{
    private final ItemStack input;
    private final Item catalyst;
    private final ItemStack output;

    public OminousBrewingRecipe(ItemStack input, Item catalyst, ItemStack output)
    {
        this.input = input;
        this.catalyst = catalyst;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input)
    {
        if (input.is(Items.EXPERIENCE_BOTTLE) && this.input.is(Items.EXPERIENCE_BOTTLE)) return true;
        if (!input.is(Items.OMINOUS_BOTTLE)) return false;
        return Objects.equals(input.get(DataComponents.OMINOUS_BOTTLE_AMPLIFIER),
                this.input.get(DataComponents.OMINOUS_BOTTLE_AMPLIFIER));
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
        return output.copy();
    }
}
