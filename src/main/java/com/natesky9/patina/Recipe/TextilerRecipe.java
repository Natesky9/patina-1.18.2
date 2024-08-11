package com.natesky9.patina.Recipe;

import com.mojang.serialization.DataResult;
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
import org.jetbrains.annotations.NotNull;

public class TextilerRecipe implements Recipe<RecipeInput> {
    public static String name = "textiler";
    private final RecipeType<?> type = ModRecipeTypes.TEXTILER_RECIPE_TYPE.get();
    private final RecipeSerializer<?> serializer = ModRecipeSerializers.TEXTILER_SERIALIZER.get();

    final NonNullList<Ingredient> inputs;
    final ItemStack output;
    //
    public TextilerRecipe(ItemStack output, NonNullList<Ingredient> inputs)
    {
        this.output = output;
        this.inputs = inputs;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    //
    @Override
    public boolean matches(RecipeInput pContainer, Level level) {
        for (int i=0;i < inputs.size(); i++)
        {
            boolean match = inputs.get(i).test(pContainer.getItem(i));
            if (!match) return false;
        }
        assemble(pContainer,level.registryAccess());
        return true;
    }

    @Override
    public ItemStack assemble(RecipeInput p_343633_, HolderLookup.Provider p_332698_) {
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
    //TODO: serializer
    public static class Serializer implements RecipeSerializer<TextilerRecipe>
    {
        final static MapCodec<TextilerRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                instance.group(
                        ItemStack.SIMPLE_ITEM_CODEC.fieldOf("output").forGetter((getter) -> getter.output),
                        Ingredient.CODEC.listOf().fieldOf("ingredients").flatXmap(
                                (map) ->
                                {
                                    NonNullList<Ingredient> list = NonNullList.create();
                                    list.addAll(map);
                                    return DataResult.success(list);
                                }, DataResult::success).forGetter((getter) -> getter.inputs
                        )
                ).apply(instance, TextilerRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf,TextilerRecipe> STREAM_CODEC =
                StreamCodec.of(Serializer::encode,Serializer::decode);

        @Override
        public MapCodec<TextilerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TextilerRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static TextilerRecipe decode(RegistryFriendlyByteBuf buffer) {
            int size = buffer.readInt();
            NonNullList<Ingredient> list = NonNullList.create();
            for (int i = 0;i < size;i++)
            {
                list.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }
            ItemStack output = ItemStack.STREAM_CODEC.decode(buffer);
            return new TextilerRecipe(output, list);
        }

        private static void encode(RegistryFriendlyByteBuf buffer, TextilerRecipe recipe) {
            buffer.writeInt(recipe.inputs.size());
            for (Ingredient ingredient: recipe.getIngredients())
            {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer,ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buffer,recipe.output);
        }
    }

}
