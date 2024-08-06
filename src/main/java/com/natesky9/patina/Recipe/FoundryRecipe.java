package com.natesky9.patina.Recipe;

import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class FoundryRecipe implements Recipe<RecipeInput> {
    public static String name = "foundry";
    private final RecipeType<?> type = ModRecipeTypes.FOUNDRY_RECIPE_TYPE.get();
    private final RecipeSerializer<?> serializer = ModRecipeSerializers.FOUNDRY_SERIALIZER.get();

    final ItemStack input;
    final Ingredient catalyst;
    final ItemStack output;
    //
    public FoundryRecipe(ItemStack output,
                         ItemStack input, Ingredient catalyst)
    {
        this.output = output;
        this.input = input;
        this.catalyst = catalyst;
    }
    //


    public ItemStack getInput() {
        return input;
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY,Ingredient.of(input),catalyst);
    }

    @Override
    public boolean matches(RecipeInput pContainer, Level pLevel) {
        //compare itemstack sizes
        ItemStack inputStack = pContainer.getItem(0);
        boolean first = inputStack.is(input.getItem()) && inputStack.getCount() >= input.getCount();
        boolean second = catalyst.test(pContainer.getItem(1));
        if (first && second) assemble(pContainer,pLevel.registryAccess());
        return first && second;
    }

    @Override
    public boolean isIncomplete() {
        //have to set this to false or else the empty ingredient will mess up recipe book
        return false;
    }

    @Override
    public ItemStack assemble(RecipeInput p_44001_, HolderLookup.Provider p_267165_) {
        //do stuff to the stack here
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }


    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_267052_) {
        return output.copy();
    }

    @Override
    public RecipeType<?> getType() {
        return type;
    }
    //Serializer stuffs
    @Override
    public RecipeSerializer<?> getSerializer() {
        return serializer;
    }
    //TODO:Serializer
    //public static class Serializer implements RecipeSerializer<FoundryRecipe>
    //{
    //    final static Codec<FoundryRecipe> CODEC = RecordCodecBuilder.create((instance) ->
    //            instance.group(
    //                    ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("output").forGetter((getter) ->  getter.output),
    //                    ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("input").forGetter((getter) -> getter.input),
    //                    Ingredient.CODEC.optionalFieldOf("catalyst",Ingredient.EMPTY).forGetter((getter) -> getter.catalyst)
    //            ).apply(instance, FoundryRecipe::new)
    //    );
//
    //    @Override
    //    public Codec<FoundryRecipe> codec() {
    //        return CODEC;
    //    }
//
    //    @Override
    //    public @Nullable FoundryRecipe fromNetwork(FriendlyByteBuf buffer) {
    //        ItemStack input = buffer.readItem();
    //        Ingredient catalyst = Ingredient.fromNetwork(buffer);
    //        ItemStack output = buffer.readItem();
    //        return new FoundryRecipe(output, input, catalyst);
    //    }
//
    //    @Override
    //    public void toNetwork(FriendlyByteBuf buffer, FoundryRecipe recipe) {
    //        buffer.writeItemStack(recipe.input,false);
    //        recipe.catalyst.toNetwork(buffer);
    //        buffer.writeItemStack(recipe.output,false);
    //    }
    //}

}
