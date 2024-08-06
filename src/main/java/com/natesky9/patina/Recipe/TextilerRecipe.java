package com.natesky9.patina.Recipe;

import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
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
    //public static class Serializer implements RecipeSerializer<TextilerRecipe>
    //{
    //    final static Codec<TextilerRecipe> CODEC = RecordCodecBuilder.create((instance) ->
    //            instance.group(
    //                    ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("output").forGetter((getter) -> getter.output),
    //                    Ingredient.CODEC.listOf().fieldOf("ingredients").flatXmap(
    //                            (map) ->
    //                            {
    //                                NonNullList<Ingredient> list = NonNullList.create();
    //                                list.addAll(map);
    //                                return DataResult.success(list);
    //                            }, DataResult::success).forGetter((getter) -> getter.inputs
    //                    )
    //            ).apply(instance, TextilerRecipe::new)
    //    );
//
    //    @Override
    //    public Codec<TextilerRecipe> codec() {
    //        return CODEC;
    //    }
//
    //    @Override
    //    public @Nullable TextilerRecipe fromNetwork(FriendlyByteBuf buffer) {
    //        int size = buffer.readInt();
    //        NonNullList<Ingredient> list = NonNullList.create();
    //        for (int i = 0;i < size;i++)
    //        {
    //            list.add(Ingredient.fromNetwork(buffer));
    //        }
    //        ItemStack output = buffer.readItem();
    //        return new TextilerRecipe(output, list);
    //    }
//
    //    @Override
    //    public void toNetwork(FriendlyByteBuf buffer, TextilerRecipe recipe) {
    //        buffer.writeInt(recipe.inputs.size());
    //        for (Ingredient ingredient: recipe.getIngredients())
    //        {
    //            ingredient.toNetwork(buffer);
    //        }
    //        buffer.writeItemStack(recipe.output,false);
    //    }
    //}

}
