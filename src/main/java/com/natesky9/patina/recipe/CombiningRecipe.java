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

public class CombiningRecipe implements Recipe<SimpleContainer> {
    //recipe type
    //that takes in a tool
    //and a resource, outputting a component
    public static final String name = "combining";
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;

    public CombiningRecipe(ResourceLocation id, ItemStack output,
                           NonNullList<Ingredient> recipeItems)
    {
        this.id = id;
        this.output = output;
        this.inputs = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean component1 = inputs.get(0).test(pContainer.getItem(0));
        boolean component2 = inputs.get(1).test(pContainer.getItem(1));
        if (component1 && component1) assemble(pContainer);
        return component1 && component2;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        //gem
        ItemStack item1 = pContainer.getItem(0);
        //bolts
        ItemStack item2 = pContainer.getItem(1);

        //bolt tips + bolts
        if (item1.is(ModItems.BOLT_TIPS.get()) && item2.is(ModItems.BOLTS.get()))
        {
        output.getOrCreateTag().putString("gem",item1.getOrCreateTag().getString("gem"));
        output.getOrCreateTag().putInt("gem color", item1.getOrCreateTag().getInt("gem color"));
        output.getOrCreateTag().putString("metal",item2.getOrCreateTag().getString("metal"));
        output.getOrCreateTag().putInt("metal color", item2.getOrCreateTag().getInt("metal color"));
        output.getOrCreateTag().putString("feather",item2.getOrCreateTag().getString("feather"));
        output.getOrCreateTag().putInt("feather color",item2.getOrCreateTag().getInt("feather color"));
        output.getOrCreateTag().putString("enchantment",item1.getOrCreateTag().getString("enchantment"));
        }
        if (item1.is(ModItems.CROSSBOW_STOCK.get()) && item2.is(ModItems.CROSSBOW_LIMB.get()))
        {
            output.getOrCreateTag().putString("stock",item1.getOrCreateTag().getString("wood"));
            output.getOrCreateTag().putInt("wood color",item1.getOrCreateTag().getInt("wood color"));
            output.getOrCreateTag().putString("limbs",item2.getOrCreateTag().getString("metal"));
            output.getOrCreateTag().putInt("metal color",item2.getOrCreateTag().getInt("metal color"));
        }
        if (item1.is(ModItems.IMPERFECT_STAFF.get()) && item2.is(ModItems.ORB.get()))
        {
            output.getOrCreateTag().putString("wood",item1.getOrCreateTag().getString("wood"));
            output.getOrCreateTag().putInt("wood color",item1.getOrCreateTag().getInt("wood color"));
            output.getOrCreateTag().putString("ornament",item1.getOrCreateTag().getString("ornament"));
            output.getOrCreateTag().putInt("ornament color",item1.getOrCreateTag().getInt("ornament color"));
            output.getOrCreateTag().putString("orb",item2.getOrCreateTag().getString("orb"));
            output.getOrCreateTag().putInt("orb color",item2.getOrCreateTag().getInt("orb color"));
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
        return ModRecipeTypes.COMBINING_RECIPE_TYPE.get();
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CombiningRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public CombiningRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json,"inputs");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2,Ingredient.EMPTY);

            inputs.set(0,Ingredient.fromJson(ingredients.get(0)));
            inputs.set(1,Ingredient.fromJson(ingredients.get(1)));

            return new CombiningRecipe(pRecipeId, output, inputs);
        }

        @Nullable
        @Override
        public CombiningRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(),Ingredient.EMPTY);

            inputs.set(0, Ingredient.fromNetwork(buffer));
            inputs.set(1, Ingredient.fromNetwork(buffer));

            ItemStack output = buffer.readItem();
            return new CombiningRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CombiningRecipe pRecipe) {

            buffer.writeInt(pRecipe.getIngredients().size());

            pRecipe.getIngredients().get(0).toNetwork(buffer);
            pRecipe.getIngredients().get(1).toNetwork(buffer);

            buffer.writeItemStack(pRecipe.getResultItem(),false);
        }
    }
}
