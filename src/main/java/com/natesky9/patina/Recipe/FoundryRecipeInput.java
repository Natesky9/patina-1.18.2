package com.natesky9.patina.Recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record FoundryRecipeInput(ItemStack input, ItemStack catalyst) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch (index)
        {
            case 0 -> this.input;
            case 1 -> this.catalyst;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
        };
    }

    @Override
    public int size() {
        return 2;
    }
}
