package com.natesky9.patina.recipe;

import com.natesky9.patina.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class SaltBrewingRecipe implements IBrewingRecipe
{
    private final Potion input;
    private final MobEffect ingredient;
    private final Potion output;

    public SaltBrewingRecipe(Potion input, MobEffect effect, Potion output)
    {
        this.input = input;
        this.ingredient = effect;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input)
    {
        return this.input == PotionUtils.getPotion(input);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient)
    {
        if (!ingredient.getItem().equals(ModItems.MAGIC_SALT.get())) return false;
        //add a case for Potion Salt
        Potion potion = PotionUtils.getPotion(ingredient);
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
        itemStack.setTag(new CompoundTag());//is this needed?
        PotionUtils.setPotion(itemStack, this.output);
        return itemStack;
    }
}
