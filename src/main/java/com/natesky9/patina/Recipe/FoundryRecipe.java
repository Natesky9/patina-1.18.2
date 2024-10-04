package com.natesky9.patina.Recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class FoundryRecipe implements Recipe<RecipeInput> {
    public static String name = "foundry";
    private final RecipeType<?> type = ModRecipeTypes.FOUNDRY_RECIPE_TYPE.get();
    private final RecipeSerializer<?> serializer = ModRecipeSerializers.FOUNDRY_SERIALIZER.get();

    final Ingredient input;
    final Ingredient catalyst;
    final ItemStack output;
    //
    public FoundryRecipe(ItemStack output,
                         Ingredient input, Ingredient catalyst)
    {
        this.output = output;
        this.input = input;
        this.catalyst = catalyst;
    }
    //


    public Ingredient getInput() {
        return input;
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY,input,catalyst);
    }

    @Override
    public boolean matches(RecipeInput pContainer, Level pLevel) {
        boolean first = input.test(pContainer.getItem(0));
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
    public static class Serializer implements RecipeSerializer<FoundryRecipe>
    {
        final static MapCodec<FoundryRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                instance.group(
                        ItemStack.CODEC.fieldOf("output").forGetter((getter) ->  getter.output),
                        Ingredient.CODEC.optionalFieldOf("input",Ingredient.EMPTY).forGetter((getter) -> getter.input),
                        Ingredient.CODEC.optionalFieldOf("catalyst",Ingredient.EMPTY).forGetter((getter) -> getter.catalyst)
                ).apply(instance, FoundryRecipe::new)
        );
        public final static StreamCodec<RegistryFriendlyByteBuf,FoundryRecipe> STREAM_CODEC =
                StreamCodec.of(Serializer::encode,Serializer::decode
        );

        @Override
        public MapCodec<FoundryRecipe> codec() {
            return CODEC;
        }


        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FoundryRecipe> streamCodec() {
            return STREAM_CODEC;
        }


        private static FoundryRecipe decode(RegistryFriendlyByteBuf buffer) {
            Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient catalyst = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack output = ItemStack.STREAM_CODEC.decode(buffer);
            return new FoundryRecipe(output, input, catalyst);
        }


        private static void encode(RegistryFriendlyByteBuf buffer, FoundryRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer,recipe.input);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer,recipe.catalyst);
            ItemStack.STREAM_CODEC.encode(buffer,recipe.output);
        }
    }

}
