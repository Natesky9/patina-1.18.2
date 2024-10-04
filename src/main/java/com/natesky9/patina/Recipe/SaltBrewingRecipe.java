package com.natesky9.patina.Recipe;

import com.natesky9.patina.init.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class SaltBrewingRecipe implements IBrewingRecipe
{
    private final Holder<Potion> input;
    private final MobEffect ingredient;
    private final Holder<Potion> output;

    public SaltBrewingRecipe(Holder<Potion> input, MobEffect effect, Holder<Potion> output)
    {
        this.input = input;
        this.ingredient = effect;
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
        if (!ingredient.getItem().equals(ModItems.POTION_SALT.get())) return false;
        //add a case for Potion Salt
        Potion potion = ingredient.get(DataComponents.POTION_CONTENTS).potion().get().get();
        if (potion.getEffects().size() == 0) return false;

        return potion.getEffects().get(0).getEffect() == this.ingredient;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        if(!this.isInput(input) || !this.isIngredient(ingredient))
        {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(input.getItem());
        itemStack.set(DataComponents.POTION_CONTENTS,new PotionContents(this.output));
        //itemStack.setTag(new CompoundTag());//is this needed?
        //PotionUtils.setPotion(itemStack, this.output);
        return itemStack;
    }
}
