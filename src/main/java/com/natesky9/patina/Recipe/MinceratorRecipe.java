package com.natesky9.patina.Recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import com.natesky9.patina.init.ModTags;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MinceratorRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    public static final String name = "smoker_grindstone";
    private final ItemStack output;
    private final ArrayList<?> recipeItems;
    public static int match;

    public MinceratorRecipe(ResourceLocation id, ItemStack output,
                            ArrayList<?> recipeItems)
    {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        match = 0;
        ArrayList<ItemStack> suppliedItems = new ArrayList<>();
        for (int i=3; i >= 0;i--)
        {
            ItemStack stack = pContainer.getItem(i);
            if (stack.isEmpty()) return false;
            suppliedItems.add(stack);
            for (int f = 0; f < 5;f++)
            {//check if it's a food group
                if (stack.is(ModTags.FOOD_PAIRS.get(f).first))
                {
                    if (stack.is(ModTags.FOOD_PAIRS.get(f).second))
                        suppliedItems.add(stack);//add another one because it's a super
                    break;
                }
            }
        }
        if (suppliedItems.size() < recipeItems.size()) return false;
        boolean matches = false;//have to initialize or else it yells at me
        ItemStack holder;
        for (int i = 0;i < recipeItems.size();i++)
        {
            for (int r = i; r < suppliedItems.size();r++)
            {
                //if it's an ingredient
                if (recipeItems.get(i) instanceof Ingredient ing)
                {
                    match += 5;
                    matches = ing.test(suppliedItems.get(r));
                }
                if (recipeItems.get(i) instanceof TagKey ing)
                {
                    match += 1;
                    matches = suppliedItems.get(r).is(ing);
                }
                //matches = recipeItems.get(i).test(suppliedItems.get(r));
                if (matches)
                {
                    holder = suppliedItems.get(i);
                    suppliedItems.set(i,suppliedItems.get(r));
                    suppliedItems.set(r,holder);
                    break;
                }
            }
            if (!matches) return false;
        }
        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess access) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.MINCERATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipeTypes.MINCERATOR_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<MinceratorRecipe> {

        @Override
        public MinceratorRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
            @Nullable ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            ArrayList<Object> inputs = new ArrayList<>();

            for (int i = 0; i < ingredients.size(); i++) {
                JsonObject test = ((JsonObject) ingredients.get(i));
                if (test.has("item"))
                    inputs.add(i, Ingredient.fromJson(ingredients.get(i)));
                if (test.has("tag"))
                {
                    String str = test.get("tag").getAsString();
                    ResourceLocation parse = ResourceLocation.tryParse(str);
                    TagKey<Item> tag = tagManager.createTagKey(parse);
                    int count = test.has("count") ? test.get("count").getAsInt():1;
                    for (int c=0;c < count;c++)
                        inputs.add(inputs.size(), tag);
                }
            }
            return new MinceratorRecipe(pRecipeId, output, inputs);
        }

        @Nullable
        @Override
        public MinceratorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            ArrayList<Object> inputs = new ArrayList<>();
            @Nullable ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();

            boolean isTag = buffer.readBoolean();
            int size = buffer.readInt();
            for (int i = 0; i < size; i++) {
                if (isTag)
                {
                    String tagKey = buffer.readUtf();
                    TagKey<Item> tag = tagManager.createTagKey(new ResourceLocation(tagKey));
                    inputs.set(i, tag);
                }
                else
                    inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new MinceratorRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MinceratorRecipe pRecipe) {
            buffer.writeInt(pRecipe.getIngredients().size());
            for (Object object : pRecipe.getIngredients())
            {
                if (object instanceof Ingredient ingredient)
                {
                    buffer.writeBoolean(false);
                    ingredient.toNetwork(buffer);
                }
                if (object instanceof TagKey<?> tagKey)
                {
                    buffer.writeBoolean(true);
                    buffer.writeUtf(tagKey.location().toString());
                }
            }
            buffer.writeItemStack(pRecipe.output, false);
        }
    }
}
