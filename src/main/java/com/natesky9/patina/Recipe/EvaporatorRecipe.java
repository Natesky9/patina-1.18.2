package com.natesky9.patina.Recipe;

import com.google.gson.JsonObject;
import com.natesky9.patina.init.ModPotions;
import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
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
    public final Potion inputPotion;
    //
    public EvaporatorRecipe(ResourceLocation id, ItemStack output,
                            Ingredient input)
    {
        this.id = id;
        this.output = output;
        this.input = input;
        this.inputPotion = null;
    }
    public EvaporatorRecipe(ResourceLocation id, ItemStack output,
                            Potion inputPotion)
    {//for specific potions
        this.id = id;
        this.output = output;
        this.input = null;
        this.inputPotion = inputPotion;
    }
    //
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (inputPotion == null)
        {

            boolean flag = input.test(pContainer.getItem(0));
            ItemStack potion = pContainer.getItem(0);
            boolean test = PotionUtils.getPotion(potion).getEffects().stream().allMatch((effectInstance -> effectInstance.getAmplifier() == 0));
            boolean effect = PotionUtils.getPotion(potion).getEffects().size() == 1;
            if (flag && effect && test) assemble(pContainer,pLevel.registryAccess());
            return flag && effect && test;
        }
        if (input == null)
        {
            Potion potion = PotionUtils.getPotion(pContainer.getItem(0));
            boolean flag = potion == inputPotion;
            if (flag) assemble(pContainer,pLevel.registryAccess());
            return flag;
        }
        return false;
        //we don't have anything here yet

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
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess p_267165_) {
        //do stuff to the stack here
        if (inputPotion == null)
            PotionUtils.setPotion(output,PotionUtils.getPotion(container.getItem(0)));
        //if (input == null) do nothing as we're outputting a stack
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
            boolean specific = json.has("potion");
            if (specific)
            {
                Potion potion = Potion.byName(GsonHelper.getAsString(json,"potion"));
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"output"));
                return new EvaporatorRecipe(id,output,potion);
            }
            else
            {
                Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input"));
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
                return new EvaporatorRecipe(id, output, input);
            }
        }

        @Override
        public @Nullable EvaporatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            boolean specific = buffer.readBoolean();
            if (specific)
            {
                Potion potion = buffer.readById(BuiltInRegistries.POTION);
                ItemStack output = buffer.readItem();
                return new EvaporatorRecipe(id, output, potion);
            }
            Ingredient input = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            return new EvaporatorRecipe(id, output, input);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EvaporatorRecipe recipe) {
            boolean specific = recipe.input == null;
            buffer.writeBoolean(specific);
            if (specific)
            {
                buffer.writeId(BuiltInRegistries.POTION,recipe.inputPotion);
                buffer.writeItemStack(recipe.output,false);
                return;
            }
            recipe.input.toNetwork(buffer);
            buffer.writeItemStack(recipe.output,false);
        }
    }

}
