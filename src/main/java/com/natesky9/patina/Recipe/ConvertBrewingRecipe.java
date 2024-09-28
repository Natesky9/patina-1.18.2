package com.natesky9.patina.Recipe;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class ConvertBrewingRecipe implements IBrewingRecipe
{
    private final Potion input;
    private final Item catalyst;
    private final Item output;

    public ConvertBrewingRecipe(Potion input, Item catalyst, Item output)
    {
        this.input = input;
        this.catalyst = catalyst;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input)
    {
        if (!input.has(DataComponents.POTION_CONTENTS)) return false;
        return this.input == input.get(DataComponents.POTION_CONTENTS).potion().get();
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

        //itemStack.setTag(new CompoundTag());//is this needed?
        //PotionUtils.setPotion(itemStack, this.output);
        return new ItemStack(output);
    }
}
