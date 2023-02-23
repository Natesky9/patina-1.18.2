package com.natesky9.patina.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class FinishingRecipe implements Recipe<SimpleContainer> {
    //recipe type
    //takes in an nbt item and a normal item
    //returns the completed item
    private final ResourceLocation id;
    public static final String name = "finishing";
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;
    private int color;

    public FinishingRecipe(ResourceLocation id, ItemStack output,
                           NonNullList<Ingredient> recipeItems, int color)
    {
        this.id = id;
        this.output = output;
        this.inputs = recipeItems;
        this.color = color;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean component1 = inputs.get(0).test(pContainer.getItem(0));
        boolean component2 = inputs.get(1).test(pContainer.getItem(1));
        if (component1 && component2) assemble(pContainer);
        return component1 && component2;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        //gem
        ItemStack item1 = pContainer.getItem(0);
        //bolts
        ItemStack item2 = pContainer.getItem(1);


        if (item1.is(ModItems.UNFINISHED_BOLTS.get()))
        {
            output.getOrCreateTag().putString("metal", item1.getOrCreateTag().getString("metal"));
            output.getOrCreateTag().putInt("metal color", item1.getOrCreateTag().getInt("metal color"));
            output.getOrCreateTag().putString("feather",item2.getItem().getCreatorModId(item2) + ":" + item2.getItem());
            output.getOrCreateTag().putInt("feather color", color);
        }
        if (item1.is(ModItems.UNSTRUNG_CROSSBOW.get()))
        {
            output.getOrCreateTag().putString("metal", item1.getOrCreateTag().getString("metal"));
            output.getOrCreateTag().putInt("metal color", item1.getOrCreateTag().getInt("metal color"));
            output.getOrCreateTag().putString("wood", item1.getOrCreateTag().getString("wood"));
            output.getOrCreateTag().putInt("wood color", item1.getOrCreateTag().getInt("wood color"));
            output.getOrCreateTag().putString("string", item2.getItem().getCreatorModId(item2) + ":" + item2.getItem());
            output.getOrCreateTag().putInt("string color",color);
        }
        if (item1.is(ModItems.UNFINISHED_STAFF.get()))
        {
            output.getOrCreateTag().putString("wood", item1.getOrCreateTag().getString("wood"));
            output.getOrCreateTag().putInt("wood color", item1.getOrCreateTag().getInt("wood color"));
            output.getOrCreateTag().putString("ornament",item2.getItem().getCreatorModId(item2) + ":" + item2.getItem());
            output.getOrCreateTag().putInt("ornament color", color);
        }

            System.out.println(output);
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
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.FINISHING_RECIPE_TYPE.get();
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FinishingRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public FinishingRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json,"inputs");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2,Ingredient.EMPTY);

            inputs.set(0,Ingredient.fromJson(ingredients.get(0)));
            inputs.set(1,Ingredient.fromJson(ingredients.get(1)));
            int color = GsonHelper.getAsInt(json,"color");

            return new FinishingRecipe(pRecipeId, output, inputs,color);
        }

        @Nullable
        @Override
        public FinishingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(),Ingredient.EMPTY);

            inputs.set(0, Ingredient.fromNetwork(buffer));
            inputs.set(1, Ingredient.fromNetwork(buffer));
            int color = buffer.readInt();

            ItemStack output = buffer.readItem();
            return new FinishingRecipe(id, output, inputs,color);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FinishingRecipe pRecipe) {

            buffer.writeInt(pRecipe.getIngredients().size());

            pRecipe.getIngredients().get(0).toNetwork(buffer);
            pRecipe.getIngredients().get(1).toNetwork(buffer);
            buffer.writeInt(pRecipe.color);

            buffer.writeItemStack(pRecipe.getResultItem(),false);
        }
    }
}
