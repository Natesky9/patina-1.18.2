package com.natesky9.patina.Recipe;

import com.google.gson.JsonObject;
import com.natesky9.patina.init.ModPotions;
import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EvaporatorRecipe implements Recipe<SimpleContainer> {
    public static String name = "evaporator";
    private final RecipeType<?> type = ModRecipeTypes.EVAPORATOR_RECIPE_TYPE.get();

    private final ResourceLocation id;
    public final ItemStack output;
    public final Ingredient input;
    //
    public EvaporatorRecipe(ResourceLocation id, ItemStack output,
                            Ingredient input)
    {
        this.id = id;
        this.output = output;
        this.input = input;
    }
    //
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        ItemStack potion = pContainer.getItem(0);
        if (PotionUtils.getPotion(potion) == ModPotions.IRIDESCENT_POTION.get())
            return false;
        boolean flag = input.test(pContainer.getItem(0));
        //ItemStack potion = itemStackHandler.extractItem(input,1,false);
        //itemStackHandler.insertItem(input,new ItemStack(Items.GLASS_BOTTLE),false);
        //ItemStack result;
        //if (PotionUtils.getPotion(potion) == Potions.THICK)
        //{result = new ItemStack(Items.BONE_MEAL,9);}
        //else if (PotionUtils.getPotion(potion)== ModPotions.VOLATILE_POTION.get())
        //{result = new ItemStack(Items.GUNPOWDER,9);}
        //else if (PotionUtils.getPotion(potion) == ModPotions.IRIDESCENT_POTION.get())
        //{result = new ItemStack(ModItems.BISMUTH_NUGGET.get());}
        //else
        //{
        //    result = new ItemStack(ModItems.POTION_SALT.get());
        //    PotionUtils.setPotion(result, PotionUtils.getPotion(potion));
        //}

        //
        //boolean first = input.test(pContainer.getItem(0));
        if (flag) assemble(pContainer,pLevel.registryAccess());
        return flag;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess p_267165_) {
        //do stuff to the stack here
        PotionUtils.setPotion(output,PotionUtils.getPotion(container.getItem(0)));
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
        return ModRecipeSerializers.EVAPORATOR_SERIALIZER.get();
    }
    public static class Serializer implements RecipeSerializer<EvaporatorRecipe>
    {
        @Override
        public EvaporatorRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json,"input"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"output"));
            return new EvaporatorRecipe(id,output,input);
        }

        @Override
        public @Nullable EvaporatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            return new EvaporatorRecipe(id, output, input);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EvaporatorRecipe recipe) {
            recipe.input.toNetwork(buffer);
            buffer.writeItemStack(recipe.output,false);
        }
    }

}
