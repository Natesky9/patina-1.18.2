package com.natesky9.patina.Recipe;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import java.util.List;
import java.util.Optional;

public class ItemBrewingRecipe implements IBrewingRecipe {
    private final Holder<Potion> input;
    private final Item catalyst;
    private final Holder<Potion> output;
    private Optional<Integer> customColor = Optional.empty();

    public ItemBrewingRecipe(Holder<Potion> input, Item ingredient, Holder<Potion> output) {
        this.input = input;
        this.catalyst = ingredient;
        this.output = output;
    }
    public ItemBrewingRecipe(Holder<Potion> input, Item ingredient, Holder<Potion> output, int color)
    {
        this(input, ingredient, output);
        customColor = Optional.of(color);
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
        return ingredient.getItem() == this.catalyst;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        if(!this.isInput(input) || !this.isIngredient(ingredient))
        {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(input.getItem());
        PotionContents contents = new PotionContents(Optional.of(this.output),customColor,List.of());
        itemStack.set(DataComponents.POTION_CONTENTS,contents);
        return itemStack;
    }
}
