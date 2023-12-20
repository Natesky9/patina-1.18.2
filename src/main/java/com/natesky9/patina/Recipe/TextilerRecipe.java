package com.natesky9.patina.Recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TextilerRecipe implements Recipe<SimpleContainer> {
    public static String name = "textiler";
    private final RecipeType<?> type = ModRecipeTypes.TEXTILER_RECIPE_TYPE.get();
    private final RecipeSerializer<?> serializer = ModRecipeSerializers.TEXTILER_SERIALIZER.get();

    final Ingredient input;
    final ItemStack output;
    //
    public TextilerRecipe(ItemStack output, Ingredient input)
    {
        this.output = output;
        this.input = input;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY,input);
    }

    //
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean first = input.test(pContainer.getItem(0));
        if (first) assemble(pContainer,pLevel.registryAccess());
        return first;
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
        //do stuff to the stack here
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
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
    public static class Serializer implements RecipeSerializer<TextilerRecipe>
    {
        final static Codec<TextilerRecipe> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(
                        CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("output").forGetter((getter) -> getter.output),
                        Ingredient.CODEC.fieldOf("input").forGetter((getter) -> getter.input)
                ).apply(instance, TextilerRecipe::new)
        );

        @Override
        public Codec<TextilerRecipe> codec() {
            return CODEC;
        }

        @Override
        public @Nullable TextilerRecipe fromNetwork(FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            return new TextilerRecipe(output, input);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, TextilerRecipe recipe) {
            recipe.input.toNetwork(buffer);
            buffer.writeItemStack(recipe.output,false);
        }
    }

}
