package com.natesky9.patina;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModRecipeBookType
{
    public static RecipeBookType FOUNDRY = RecipeBookType.create("foundry");
    public static RecipeBookType MINCERATOR = RecipeBookType.create("mincerator");
    public static RecipeBookType TEXTILER = RecipeBookType.create("textiler");
    public static RecipeBookType EVAPORATOR = RecipeBookType.create("evaporator");

    public static RecipeBookCategories MINCERATOR_CATEGORY = RecipeBookCategories.create("mincerator",new ItemStack(Items.FLINT));
    public static RecipeBookCategories FOUNDRY_CATEGORY = RecipeBookCategories.create("foundry",new ItemStack(Items.FIRE_CHARGE));
    public static RecipeBookCategories TEXTILER_CATEGORY = RecipeBookCategories.create("textiler",new ItemStack(Items.STRING));
    public static RecipeBookCategories EVAPORATOR_CATEGORY = RecipeBookCategories.create("evaporator",new ItemStack(Items.SUGAR));
    static void create()
    {

    }
}
