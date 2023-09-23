package com.natesky9.patina.Recipe;

import com.google.gson.JsonObject;
import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FoundryRecipe implements Recipe<SimpleContainer> {
    public static String name = "foundry";
    private final RecipeType<?> type = ModRecipeTypes.FOUNDRY_RECIPE_TYPE.get();
    private final RecipeSerializer<?> serializer = ModRecipeSerializers.FOUNDRY_SERIALIZER.get();

    private final ResourceLocation id;
    private final Ingredient input;
    private final Ingredient catalyst;
    private final ItemStack output;
    //
    public FoundryRecipe(ResourceLocation id, ItemStack output,
                         Ingredient input, Ingredient catalyst)
    {
        this.id = id;
        this.output = output;
        this.input = input;
        this.catalyst = catalyst;
    }
    //
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean first = input.test(pContainer.getItem(0));
        boolean second = catalyst.test(pContainer.getItem(1));
        if (first && second) assemble(pContainer,pLevel.registryAccess());
        return first && second;
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
        //do stuff to the stack here
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
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
    public static class Serializer implements RecipeSerializer<FoundryRecipe>
    {
        @Override
        public FoundryRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json,"input"));
            Ingredient catalyst = Ingredient.fromJson(GsonHelper.getAsJsonObject(json,"catalyst"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"output"));
            return new FoundryRecipe(id,output,input,catalyst);
        }

        @Override
        public @Nullable FoundryRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            Ingredient catalyst = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            return new FoundryRecipe(id, output, input, catalyst);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FoundryRecipe recipe) {
            recipe.input.toNetwork(buffer);
            recipe.input.toNetwork(buffer);
            buffer.writeItemStack(recipe.output,false);
        }
    }

}
