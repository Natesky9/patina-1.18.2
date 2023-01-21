package com.natesky9.patina.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.natesky9.patina.Patina;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ToolRecipe implements Recipe<SimpleContainer> {
    private static final String name = "tool";
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;

    public ToolRecipe(ResourceLocation id, ItemStack output,
                      NonNullList<Ingredient> recipeItems)
    {
        this.id = id;
        this.output = output;
        this.inputs = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean tool = inputs.get(0).test(pContainer.getItem(0));
        boolean item = inputs.get(1).test(pContainer.getItem(1));
        return tool && item;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ToolRecipe>
    {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = name;
    }
    public static class Serializer implements RecipeSerializer<ToolRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Patina.MOD_ID,name);

        @Override
        public ToolRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json,"inputs");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2,Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {inputs.set(i,Ingredient.fromJson(ingredients.get(i)));}

            return new ToolRecipe(pRecipeId, output, inputs);
        }

        @Nullable
        @Override
        public ToolRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(),Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {inputs.set(i, Ingredient.fromNetwork(buffer));}

            ItemStack output = buffer.readItem();
            return new ToolRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ToolRecipe pRecipe) {

            buffer.writeInt(pRecipe.getIngredients().size());

            for (Ingredient ingredient: pRecipe.getIngredients())
            {ingredient.toNetwork(buffer);}

            buffer.writeItemStack(pRecipe.getResultItem(),false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }
        //why do we need this?
        private static <G> Class<G> castClass(Class<?> cls)
        {
            return (Class<G>)cls;
        }
    }
}
