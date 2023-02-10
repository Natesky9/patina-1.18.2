package com.natesky9.patina.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.natesky9.patina.Patina;
import com.natesky9.patina.PatinaArchery;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class CrossbowRecipe extends CustomRecipe {
    //recipe type
    //takes a crossbow and an ingot, upgrades it
    private static final Ingredient CROSSBOW = Ingredient.of(Items.CROSSBOW);
    private static final Ingredient INGOT = Ingredient.of(PatinaArchery.Tiers.limbs.keySet().toArray(new Item[0]));

    public CrossbowRecipe(ResourceLocation pId) {
        super(pId);
    }


    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        boolean hasCrossbow = false;
        boolean hasIngot = false;
        int j;

        for(j = 0; j < pContainer.getContainerSize(); ++j)
        {
            ItemStack item = pContainer.getItem(j);
            if (!item.isEmpty())
            {
                if (CROSSBOW.test(item))
                {
                    if (hasCrossbow) return false;
                    hasCrossbow = true;
                }
                //
                if (INGOT.test(item))
                {
                    if (hasIngot) return false;
                    hasIngot = true;
                }
            }
        }
        return hasCrossbow && hasIngot;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer) {
        ItemStack stack = new ItemStack(ModItems.CROSSBOW.get());
        CompoundTag tag = stack.getOrCreateTag();

        for (int j = 0; j < pContainer.getContainerSize(); ++j)
        {
            ItemStack item = pContainer.getItem(j);
            if (!item.isEmpty())
            {
                if (INGOT.test(item))
                {
                    String text = item.getItem().getCreatorModId(item) + ":" + item.getItem();
                    tag.putString("metal",text);
                }
            }
        }
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CrossbowRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public CrossbowRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            return new CrossbowRecipe(pRecipeId);
        }

        @Nullable
        @Override
        public CrossbowRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            return new CrossbowRecipe(pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, CrossbowRecipe pRecipe) {

        }
    }
}

