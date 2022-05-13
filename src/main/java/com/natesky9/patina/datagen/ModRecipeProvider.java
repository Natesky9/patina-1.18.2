package com.natesky9.patina.datagen;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.CHORUS_CABLE.get())
                .define('C', Items.CHORUS_FRUIT)
                .pattern("C")
                .pattern("C")
                .pattern("C")
                .unlockedBy("has_chorus_fruit",inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.CHORUS_FRUIT).build()))
                .save(consumer);
        //
        ShapelessRecipeBuilder.shapeless(ModItems.CUSTOM_NUGGET.get())
                .requires(ModItems.CUSTOM_INGOT.get())
                .unlockedBy("has_custom_ingot",inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CUSTOM_INGOT.get()).build()))
                .save(consumer);
        //
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.COAL),ModItems.COAL_COKE.get(),
                .5f,1200)
                .unlockedBy("has_blaze_rod",inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BLAZE_ROD).build()))
                .save(consumer);
    }
}
