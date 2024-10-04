package com.natesky9.patina.Recipe;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class ItemBrewingRecipe implements IBrewingRecipe {
    private final Holder<Potion> input;
    private final Item ingredient;
    private final Holder<Potion> output;

    public ItemBrewingRecipe(Holder<Potion> input, Item ingredient, Holder<Potion> output) {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input) {

        if (!input.has(DataComponents.POTION_CONTENTS)) return false;
        return input.get(DataComponents.POTION_CONTENTS).potion().get().equals(this.input);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        boolean same = ingredient.getItem() == this.ingredient;
        return ingredient.getItem() == this.ingredient;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if(!this.isInput(input) || !this.isIngredient(ingredient)) {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(input.getItem());
        itemStack.set(DataComponents.POTION_CONTENTS,new PotionContents(this.output));
        //itemStack.setTag(new CompoundTag());
        //PotionUtils.setPotion(itemStack, this.output);
        return itemStack;
    }
}
