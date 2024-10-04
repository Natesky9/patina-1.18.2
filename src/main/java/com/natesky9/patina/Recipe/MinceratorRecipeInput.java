package com.natesky9.patina.Recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record MinceratorRecipeInput(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch (index)
        {
            case 0 -> this.input1;
            case 1 -> this.input2;
            case 2 -> this.input3;
            case 3 -> this.input4;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
        };
    }

    @Override
    public int size() {
        return 4;
    }
}
