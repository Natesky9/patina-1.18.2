package com.natesky9.patina.Recipe;

import com.mojang.datafixers.util.Either;
import com.natesky9.patina.init.ModRecipeSerializers;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class EvaporatorRecipe implements Recipe<RecipeInput> {
    public static String name = "evaporator";
    private final RecipeType<?> type = ModRecipeTypes.EVAPORATOR_RECIPE_TYPE.get();

    public final ItemStack output;
    public final Ingredient inputIngredient;
    public final Potion inputPotion;
    //
    public EvaporatorRecipe(ItemStack output, Either<Ingredient,Potion> either)
    {
        this.output = output;
        this.inputIngredient = either.left().isPresent() ? either.left().get() : null;
        this.inputPotion = either.right().isPresent() ? either.right().get() : null;
    }
    public EvaporatorRecipe(ItemStack output, Ingredient input)
    {
        this.output = output;
        this.inputIngredient = input;
        this.inputPotion = null;
    }
    public EvaporatorRecipe(ItemStack output, Potion input)
    {
        this.output = output;
        this.inputIngredient = null;
        this.inputPotion = input;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        if (inputIngredient != null)
            return NonNullList.of(Ingredient.EMPTY,inputIngredient);
        return NonNullList.of(Ingredient.EMPTY,Ingredient.of(Items.POTION));
    }

    @Override
    public boolean matches(RecipeInput pContainer, Level pLevel) {
        if (inputPotion == null)
        {

            boolean flag = inputIngredient.test(pContainer.getItem(0));
            ItemStack potion = pContainer.getItem(0);
            //this needs to be fixed
            //boolean test = PotionContents.(potion).getEffects().stream().allMatch((effectInstance -> effectInstance.getAmplifier() == 0));
            boolean effect = potion.get(DataComponents.POTION_CONTENTS).hasEffects();
            if (flag && effect && true/*test*/) assemble(pContainer,pLevel.registryAccess());
            return flag && effect;// && test;
        }
        if (inputIngredient == null)
        {
            Potion potion = pContainer.getItem(0).get(DataComponents.POTION_CONTENTS).potion().get().get();//PotionUtils.getPotion(pContainer.getItem(0));
            boolean flag = potion == inputPotion;
            if (flag) assemble(pContainer,pLevel.registryAccess());
            return flag;
        }
        return false;
    }

    @Override
    public ItemStack assemble(RecipeInput container, HolderLookup.Provider provider) {
        //do stuff to the stack here
        if (inputPotion == null)
            output.set(DataComponents.POTION_CONTENTS,container.getItem(0).get(DataComponents.POTION_CONTENTS));
        //if (input == null) do nothing as we're outputting a stack
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_331967_) {
        return output.copy();
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
    //TODO: Serializer
    //public static class Serializer implements RecipeSerializer<EvaporatorRecipe>
    //{
    //    public static final Codec<Either<Ingredient,Potion>> ITEM_OR_POTION_CODEC =
    //            Codec.either(Ingredient.CODEC, BuiltInRegistries.POTION.byNameCodec());
//
    //    final Codec<EvaporatorRecipe> CODEC = RecordCodecBuilder.create((instance) ->
//
    //        instance.group(
    //                ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("output").forGetter((getter) -> getter.output),
    //                ITEM_OR_POTION_CODEC.fieldOf("input").forGetter((getter) ->
    //                        getter.inputIngredient != null ? Either.left(getter.inputIngredient) : Either.right(getter.inputPotion))
    //        ).apply(instance, EvaporatorRecipe::new)
    //    );
//
//
    //    @Override
    //    public Codec<EvaporatorRecipe> codec() {
    //        return CODEC;
    //    }
//
    //    @Override
    //    public @Nullable EvaporatorRecipe fromNetwork(FriendlyByteBuf buffer) {
    //        boolean specific = buffer.readBoolean();
    //        if (specific)
    //        {
    //            Potion potion = buffer.readById(BuiltInRegistries.POTION);
    //            ItemStack output = buffer.readItem();
    //            return new EvaporatorRecipe(output, potion);
    //        }
    //        Ingredient input = Ingredient.fromNetwork(buffer);
    //        ItemStack output = buffer.readItem();
    //        return new EvaporatorRecipe(output, input);
    //    }
//
    //    @Override
    //    public void toNetwork(FriendlyByteBuf buffer, EvaporatorRecipe recipe) {
    //        boolean specific = recipe.inputIngredient == null;
    //        buffer.writeBoolean(specific);
    //        if (specific)
    //        {
    //            buffer.writeId(BuiltInRegistries.POTION,recipe.inputPotion);
    //            buffer.writeItemStack(recipe.output,false);
    //            return;
    //        }
    //        recipe.inputIngredient.toNetwork(buffer);
    //        buffer.writeItemStack(recipe.output,false);
    //    }
    //}

}
